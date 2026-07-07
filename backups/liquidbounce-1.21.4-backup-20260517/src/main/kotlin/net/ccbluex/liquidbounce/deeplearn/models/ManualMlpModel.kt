/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015 - 2025 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 */
package net.ccbluex.liquidbounce.deeplearn.models

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.Random
import net.ccbluex.liquidbounce.utils.client.logger
import kotlin.math.sqrt

private const val BATCH_NORM_EPSILON = 1.0e-5f

/**
 * Minimal inference path for LiquidBounce's bundled Minarai MLP params.
 *
 * DJL's TFLite engine can run flatbuffer TFLite models, but it cannot load DJL dynamic
 * blocks from `.params`. The bundled rotation models are a fixed MLP, so we can read
 * the DJL parameter arrays and run the same feed-forward path without a DJL block.
 */
class ManualMlpModel private constructor(
    private val layer1: LinearLayer,
    private val norm1: BatchNormLayer,
    private val layer2: LinearLayer,
    private val norm2: BatchNormLayer,
    private val layer3: LinearLayer,
    private val norm3: BatchNormLayer,
    private val outputLayer: LinearLayer
) {

    fun predict(input: FloatArray): FloatArray {
        require(input.size == layer1.inputs) {
            "Expected ${layer1.inputs} inputs, got ${input.size}"
        }

        val hidden1 = norm1.apply(layer1.apply(input)).relu()
        val hidden2 = norm2.apply(layer2.apply(hidden1)).relu()
        val hidden3 = norm3.apply(layer3.apply(hidden2)).relu()

        return outputLayer.apply(hidden3)
    }

    private fun FloatArray.relu(): FloatArray {
        for (i in indices) {
            if (this[i] < 0f) {
                this[i] = 0f
            }
        }

        return this
    }

    private class LinearLayer(
        val weight: FloatArray,
        val bias: FloatArray,
        val inputs: Int
    ) {
        private val outputs = bias.size

        init {
            require(weight.size == outputs * inputs) {
                "Linear weight shape mismatch: ${weight.size} != $outputs * $inputs"
            }
        }

        fun apply(input: FloatArray): FloatArray {
            val output = FloatArray(outputs)

            for (row in 0 until outputs) {
                var sum = bias[row]
                val offset = row * inputs

                for (column in 0 until inputs) {
                    sum += weight[offset + column] * input[column]
                }

                output[row] = sum
            }

            return output
        }
    }

    private class BatchNormLayer(
        val gamma: FloatArray,
        val beta: FloatArray,
        val runningMean: FloatArray,
        val runningVar: FloatArray
    ) {
        fun apply(input: FloatArray): FloatArray {
            val output = FloatArray(input.size)

            for (i in input.indices) {
                val normalized = (input[i] - runningMean[i]) / sqrt(runningVar[i] + BATCH_NORM_EPSILON)
                output[i] = gamma[i] * normalized + beta[i]
            }

            return output
        }
    }

    fun toDjlParams(): ByteArray {
        return encodeTensors(
            listOf(
                Tensor(null, longArrayOf(128, 6), layer1.weight),
                Tensor(null, longArrayOf(128), layer1.bias),
                Tensor(null, longArrayOf(128), norm1.gamma),
                Tensor(null, longArrayOf(128), norm1.beta),
                Tensor(null, longArrayOf(128), norm1.runningMean),
                Tensor(null, longArrayOf(128), norm1.runningVar),
                Tensor(null, longArrayOf(64, 128), layer2.weight),
                Tensor(null, longArrayOf(64), layer2.bias),
                Tensor(null, longArrayOf(64), norm2.gamma),
                Tensor(null, longArrayOf(64), norm2.beta),
                Tensor(null, longArrayOf(64), norm2.runningMean),
                Tensor(null, longArrayOf(64), norm2.runningVar),
                Tensor(null, longArrayOf(32, 64), layer3.weight),
                Tensor(null, longArrayOf(32), layer3.bias),
                Tensor(null, longArrayOf(32), norm3.gamma),
                Tensor(null, longArrayOf(32), norm3.beta),
                Tensor(null, longArrayOf(32), norm3.runningMean),
                Tensor(null, longArrayOf(32), norm3.runningVar),
                Tensor(null, longArrayOf(2, 32), outputLayer.weight),
                Tensor(null, longArrayOf(2), outputLayer.bias)
            )
        )
    }

    private data class Tensor(val name: String?, val shape: LongArray, val data: FloatArray) {
        override fun equals(other: Any?): Boolean {
            return other is Tensor &&
                name == other.name &&
                shape.contentEquals(other.shape) &&
                data.contentEquals(other.data)
        }

        override fun hashCode(): Int {
            var result = name?.hashCode() ?: 0
            result = 31 * result + shape.contentHashCode()
            result = 31 * result + data.contentHashCode()
            return result
        }
    }

    companion object {
        private const val INPUTS = 6
        private const val OUTPUTS = 2
        private const val FULL_H1 = 128
        private const val FULL_H2 = 64
        private const val FULL_H3 = 32
        private const val MOBILE_H1 = 32
        private const val MOBILE_H2 = 16
        private const val MOBILE_H3 = 8
        private const val MOBILE_EPOCHS = 70
        private const val LEARNING_RATE = 0.001f
        private const val BETA1 = 0.9f
        private const val BETA2 = 0.999f
        private const val ADAM_EPSILON = 1.0e-8f

        private val tensorMagic = byteArrayOf(
            0,
            4,
            'N'.code.toByte(),
            'D'.code.toByte(),
            'A'.code.toByte(),
            'R'.code.toByte()
        )

        fun train(features: Array<FloatArray>, labels: Array<FloatArray>, outputs: Long): ManualMlpModel {
            require(outputs == OUTPUTS.toLong()) { "Manual mobile trainer expects $OUTPUTS outputs, got $outputs" }
            return MobileTrainer(features, labels).train()
        }

        fun fromDjlParams(bytes: ByteArray): ManualMlpModel {
            val tensors = decodeTensors(bytes)
            require(tensors.size >= 20) { "Expected at least 20 tensors, got ${tensors.size}" }

            return ManualMlpModel(
                layer1 = LinearLayer(tensors[0].requireShape(128, 6).data, tensors[1].requireShape(128).data, 6),
                norm1 = BatchNormLayer(
                    tensors[2].requireShape(128).data,
                    tensors[3].requireShape(128).data,
                    tensors[4].requireShape(128).data,
                    tensors[5].requireShape(128).data
                ),
                layer2 = LinearLayer(tensors[6].requireShape(64, 128).data, tensors[7].requireShape(64).data, 128),
                norm2 = BatchNormLayer(
                    tensors[8].requireShape(64).data,
                    tensors[9].requireShape(64).data,
                    tensors[10].requireShape(64).data,
                    tensors[11].requireShape(64).data
                ),
                layer3 = LinearLayer(tensors[12].requireShape(32, 64).data, tensors[13].requireShape(32).data, 64),
                norm3 = BatchNormLayer(
                    tensors[14].requireShape(32).data,
                    tensors[15].requireShape(32).data,
                    tensors[16].requireShape(32).data,
                    tensors[17].requireShape(32).data
                ),
                outputLayer = LinearLayer(tensors[18].requireShape(2, 32).data, tensors[19].requireShape(2).data, 32)
            )
        }

        private fun Tensor.requireShape(vararg expected: Long): Tensor {
            require(shape.contentEquals(expected)) {
                "Tensor '$name' shape mismatch: ${shape.contentToString()} != ${expected.contentToString()}"
            }

            return this
        }

        private fun encodeTensors(tensors: List<Tensor>): ByteArray {
            val output = ByteArrayOutputStream()

            DataOutputStream(output).use { stream ->
                for (tensor in tensors) {
                    stream.writeUTF("NDAR")
                    stream.writeInt(3)
                    stream.writeByte(0)
                    stream.writeUTF("DENSE")
                    stream.writeUTF("FLOAT32")
                    stream.writeInt(tensor.shape.size)

                    for (dimension in tensor.shape) {
                        stream.writeLong(dimension)
                    }

                    stream.writeInt(0)
                    stream.writeByte('<'.code)

                    val dataBytes = ByteBuffer
                        .allocate(tensor.data.size * Float.SIZE_BYTES)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .apply {
                            for (value in tensor.data) {
                                putFloat(value)
                            }
                        }
                        .array()

                    stream.writeInt(dataBytes.size)
                    stream.write(dataBytes)
                }
            }

            return output.toByteArray()
        }

        private fun decodeTensors(bytes: ByteArray): List<Tensor> {
            val tensors = mutableListOf<Tensor>()
            var index = 0

            while (index < bytes.size) {
                val offset = bytes.indexOf(tensorMagic, index)

                if (offset == -1) {
                    break
                }

                tensors += decodeTensor(bytes, offset)
                index = offset + tensorMagic.size
            }

            return tensors
        }

        private fun decodeTensor(bytes: ByteArray, offset: Int): Tensor {
            val input = DataInputStream(ByteArrayInputStream(bytes, offset, bytes.size - offset))

            require(input.readUTF() == "NDAR") { "Malformed tensor data" }

            val version = input.readInt()
            require(version in 1..3) { "Unsupported tensor version $version" }

            var name: String? = null
            if (version > 1 && input.readByte().toInt() == 1) {
                name = input.readUTF()
            }

            val sparseFormat = input.readUTF()
            val dataType = input.readUTF()
            require(sparseFormat == "DENSE") { "Unsupported sparse format $sparseFormat" }
            require(dataType == "FLOAT32") { "Unsupported data type $dataType" }

            val shape = LongArray(input.readInt()) {
                input.readLong()
            }

            val layoutSize = input.readInt()
            repeat(layoutSize) {
                input.readChar()
            }

            val byteOrder = if (version > 2 && input.readByte().toInt() == '>'.code) {
                ByteOrder.BIG_ENDIAN
            } else {
                ByteOrder.LITTLE_ENDIAN
            }

            val dataSize = input.readInt()
            val dataBytes = ByteArray(dataSize)
            input.readFully(dataBytes)

            val buffer = ByteBuffer.wrap(dataBytes).order(byteOrder)
            val data = FloatArray(dataSize / Float.SIZE_BYTES) {
                buffer.float
            }

            return Tensor(name, shape, data)
        }

        private fun ByteArray.indexOf(pattern: ByteArray, startIndex: Int): Int {
            for (i in startIndex..size - pattern.size) {
                var matches = true

                for (j in pattern.indices) {
                    if (this[i + j] != pattern[j]) {
                        matches = false
                        break
                    }
                }

                if (matches) {
                    return i
                }
            }

            return -1
        }

        private class MobileTrainer(
            private val features: Array<FloatArray>,
            private val labels: Array<FloatArray>
        ) {
            private val random = Random(0x0B11_1E7L)
            private val inputMean: FloatArray
            private val inputStd: FloatArray
            private val labelMean: FloatArray
            private val labelStd: FloatArray

            private val w1 = xavier(MOBILE_H1, INPUTS)
            private val b1 = FloatArray(MOBILE_H1)
            private val w2 = xavier(MOBILE_H2, MOBILE_H1)
            private val b2 = FloatArray(MOBILE_H2)
            private val w3 = xavier(MOBILE_H3, MOBILE_H2)
            private val b3 = FloatArray(MOBILE_H3)
            private val w4 = xavier(OUTPUTS, MOBILE_H3)
            private val b4 = FloatArray(OUTPUTS)

            private val mw1 = FloatArray(w1.size)
            private val vw1 = FloatArray(w1.size)
            private val mb1 = FloatArray(b1.size)
            private val vb1 = FloatArray(b1.size)
            private val mw2 = FloatArray(w2.size)
            private val vw2 = FloatArray(w2.size)
            private val mb2 = FloatArray(b2.size)
            private val vb2 = FloatArray(b2.size)
            private val mw3 = FloatArray(w3.size)
            private val vw3 = FloatArray(w3.size)
            private val mb3 = FloatArray(b3.size)
            private val vb3 = FloatArray(b3.size)
            private val mw4 = FloatArray(w4.size)
            private val vw4 = FloatArray(w4.size)
            private val mb4 = FloatArray(b4.size)
            private val vb4 = FloatArray(b4.size)

            private val gw1 = FloatArray(w1.size)
            private val gb1 = FloatArray(b1.size)
            private val gw2 = FloatArray(w2.size)
            private val gb2 = FloatArray(b2.size)
            private val gw3 = FloatArray(w3.size)
            private val gb3 = FloatArray(b3.size)
            private val gw4 = FloatArray(w4.size)
            private val gb4 = FloatArray(b4.size)

            private val x = FloatArray(INPUTS)
            private val y = FloatArray(OUTPUTS)
            private val z1 = FloatArray(MOBILE_H1)
            private val h1 = FloatArray(MOBILE_H1)
            private val z2 = FloatArray(MOBILE_H2)
            private val h2 = FloatArray(MOBILE_H2)
            private val z3 = FloatArray(MOBILE_H3)
            private val h3 = FloatArray(MOBILE_H3)
            private val output = FloatArray(OUTPUTS)
            private val dout = FloatArray(OUTPUTS)
            private val dh3 = FloatArray(MOBILE_H3)
            private val dz3 = FloatArray(MOBILE_H3)
            private val dh2 = FloatArray(MOBILE_H2)
            private val dz2 = FloatArray(MOBILE_H2)
            private val dh1 = FloatArray(MOBILE_H1)
            private val dz1 = FloatArray(MOBILE_H1)
            private val indices = IntArray(features.size) { it }

            private var step = 0

            init {
                require(features.size == labels.size) { "Features and labels must have the same size" }
                require(features.isNotEmpty()) { "Features and labels must not be empty" }
                require(features.all { it.size == INPUTS }) { "Manual trainer expects $INPUTS input features" }
                require(labels.all { it.size == OUTPUTS }) { "Manual trainer expects $OUTPUTS labels" }

                val inputStats = meanAndStd(features, INPUTS)
                inputMean = inputStats.first
                inputStd = inputStats.second

                val labelStats = meanAndStd(labels, OUTPUTS)
                labelMean = labelStats.first
                labelStd = labelStats.second
            }

            fun train(): ManualMlpModel {
                logger.info(
                    "[DeepLearning] DJL training is unavailable; using mobile CPU trainer " +
                        "($MOBILE_EPOCHS epochs, ${features.size} samples)."
                )

                for (epoch in 0 until MOBILE_EPOCHS) {
                    shuffle(indices)
                    var loss = 0.0

                    for (index in indices) {
                        loss += trainSample(features[index], labels[index])
                    }

                    if (epoch == 0 || (epoch + 1) % 10 == 0 || epoch == MOBILE_EPOCHS - 1) {
                        logger.info(
                            "[DeepLearning] Mobile training epoch ${epoch + 1}/$MOBILE_EPOCHS " +
                                "loss=${loss / features.size}"
                        )
                    }
                }

                return exportModel()
            }

            private fun trainSample(feature: FloatArray, label: FloatArray): Float {
                normalize(feature, inputMean, inputStd, x)
                normalize(label, labelMean, labelStd, y)
                forward()

                var loss = 0f
                for (i in 0 until OUTPUTS) {
                    val diff = output[i] - y[i]
                    loss += diff * diff
                    dout[i] = 2f * diff / OUTPUTS
                }

                zeroGradients()
                backward()
                updateParameters()

                return loss / OUTPUTS
            }

            private fun forward() {
                linear(w1, b1, INPUTS, MOBILE_H1, x, z1)
                relu(z1, h1)
                linear(w2, b2, MOBILE_H1, MOBILE_H2, h1, z2)
                relu(z2, h2)
                linear(w3, b3, MOBILE_H2, MOBILE_H3, h2, z3)
                relu(z3, h3)
                linear(w4, b4, MOBILE_H3, OUTPUTS, h3, output)
            }

            private fun backward() {
                dh3.fill(0f)
                for (row in 0 until OUTPUTS) {
                    gb4[row] = dout[row]
                    val offset = row * MOBILE_H3

                    for (column in 0 until MOBILE_H3) {
                        gw4[offset + column] = dout[row] * h3[column]
                        dh3[column] += dout[row] * w4[offset + column]
                    }
                }

                dh2.fill(0f)
                for (row in 0 until MOBILE_H3) {
                    dz3[row] = if (z3[row] > 0f) dh3[row] else 0f
                    gb3[row] = dz3[row]
                    val offset = row * MOBILE_H2

                    for (column in 0 until MOBILE_H2) {
                        gw3[offset + column] = dz3[row] * h2[column]
                        dh2[column] += dz3[row] * w3[offset + column]
                    }
                }

                dh1.fill(0f)
                for (row in 0 until MOBILE_H2) {
                    dz2[row] = if (z2[row] > 0f) dh2[row] else 0f
                    gb2[row] = dz2[row]
                    val offset = row * MOBILE_H1

                    for (column in 0 until MOBILE_H1) {
                        gw2[offset + column] = dz2[row] * h1[column]
                        dh1[column] += dz2[row] * w2[offset + column]
                    }
                }

                for (row in 0 until MOBILE_H1) {
                    dz1[row] = if (z1[row] > 0f) dh1[row] else 0f
                    gb1[row] = dz1[row]
                    val offset = row * INPUTS

                    for (column in 0 until INPUTS) {
                        gw1[offset + column] = dz1[row] * x[column]
                    }
                }
            }

            private fun zeroGradients() {
                gw1.fill(0f)
                gb1.fill(0f)
                gw2.fill(0f)
                gb2.fill(0f)
                gw3.fill(0f)
                gb3.fill(0f)
                gw4.fill(0f)
                gb4.fill(0f)
            }

            private fun updateParameters() {
                step++
                updateAdam(w1, gw1, mw1, vw1)
                updateAdam(b1, gb1, mb1, vb1)
                updateAdam(w2, gw2, mw2, vw2)
                updateAdam(b2, gb2, mb2, vb2)
                updateAdam(w3, gw3, mw3, vw3)
                updateAdam(b3, gb3, mb3, vb3)
                updateAdam(w4, gw4, mw4, vw4)
                updateAdam(b4, gb4, mb4, vb4)
            }

            private fun updateAdam(parameters: FloatArray, gradients: FloatArray, firstMoment: FloatArray, secondMoment: FloatArray) {
                val beta1Correction = 1.0 - Math.pow(BETA1.toDouble(), step.toDouble())
                val beta2Correction = 1.0 - Math.pow(BETA2.toDouble(), step.toDouble())
                val stepSize = LEARNING_RATE * sqrt(beta2Correction).toFloat() / beta1Correction.toFloat()

                for (i in parameters.indices) {
                    val gradient = gradients[i]

                    firstMoment[i] = BETA1 * firstMoment[i] + (1f - BETA1) * gradient
                    secondMoment[i] = BETA2 * secondMoment[i] + (1f - BETA2) * gradient * gradient
                    parameters[i] -= stepSize * firstMoment[i] / (sqrt(secondMoment[i]) + ADAM_EPSILON)
                }
            }

            private fun exportModel(): ManualMlpModel {
                val fullW1 = FloatArray(FULL_H1 * INPUTS)
                val fullB1 = FloatArray(FULL_H1)

                for (row in 0 until MOBILE_H1) {
                    var bias = b1[row]
                    val sourceOffset = row * INPUTS
                    val targetOffset = row * INPUTS

                    for (column in 0 until INPUTS) {
                        val normalizedWeight = w1[sourceOffset + column] / inputStd[column]
                        fullW1[targetOffset + column] = normalizedWeight
                        bias -= w1[sourceOffset + column] * inputMean[column] / inputStd[column]
                    }

                    fullB1[row] = bias
                }

                val fullW2 = FloatArray(FULL_H2 * FULL_H1)
                val fullB2 = FloatArray(FULL_H2)
                for (row in 0 until MOBILE_H2) {
                    fullB2[row] = b2[row]
                    for (column in 0 until MOBILE_H1) {
                        fullW2[row * FULL_H1 + column] = w2[row * MOBILE_H1 + column]
                    }
                }

                val fullW3 = FloatArray(FULL_H3 * FULL_H2)
                val fullB3 = FloatArray(FULL_H3)
                for (row in 0 until MOBILE_H3) {
                    fullB3[row] = b3[row]
                    for (column in 0 until MOBILE_H2) {
                        fullW3[row * FULL_H2 + column] = w3[row * MOBILE_H2 + column]
                    }
                }

                val fullW4 = FloatArray(OUTPUTS * FULL_H3)
                val fullB4 = FloatArray(OUTPUTS)
                for (row in 0 until OUTPUTS) {
                    fullB4[row] = b4[row] * labelStd[row] + labelMean[row]
                    for (column in 0 until MOBILE_H3) {
                        fullW4[row * FULL_H3 + column] = w4[row * MOBILE_H3 + column] * labelStd[row]
                    }
                }

                return ManualMlpModel(
                    LinearLayer(fullW1, fullB1, INPUTS),
                    identityBatchNorm(FULL_H1),
                    LinearLayer(fullW2, fullB2, FULL_H1),
                    identityBatchNorm(FULL_H2),
                    LinearLayer(fullW3, fullB3, FULL_H2),
                    identityBatchNorm(FULL_H3),
                    LinearLayer(fullW4, fullB4, FULL_H3)
                )
            }

            private fun xavier(outputs: Int, inputs: Int): FloatArray {
                val limit = sqrt(6f / (outputs + inputs))
                return FloatArray(outputs * inputs) {
                    (random.nextFloat() * 2f - 1f) * limit
                }
            }

            private fun shuffle(indices: IntArray) {
                for (i in indices.lastIndex downTo 1) {
                    val j = random.nextInt(i + 1)
                    val tmp = indices[i]
                    indices[i] = indices[j]
                    indices[j] = tmp
                }
            }

            private fun meanAndStd(rows: Array<FloatArray>, size: Int): Pair<FloatArray, FloatArray> {
                val mean = FloatArray(size)
                val std = FloatArray(size)

                for (row in rows) {
                    for (i in 0 until size) {
                        mean[i] += row[i]
                    }
                }

                for (i in 0 until size) {
                    mean[i] /= rows.size
                }

                for (row in rows) {
                    for (i in 0 until size) {
                        val diff = row[i] - mean[i]
                        std[i] += diff * diff
                    }
                }

                for (i in 0 until size) {
                    std[i] = sqrt(std[i] / rows.size).coerceAtLeast(1.0e-3f)
                }

                return mean to std
            }

            private fun normalize(input: FloatArray, mean: FloatArray, std: FloatArray, output: FloatArray) {
                for (i in output.indices) {
                    output[i] = (input[i] - mean[i]) / std[i]
                }
            }

            private fun linear(
                weight: FloatArray,
                bias: FloatArray,
                inputs: Int,
                outputs: Int,
                input: FloatArray,
                output: FloatArray
            ) {
                for (row in 0 until outputs) {
                    var sum = bias[row]
                    val offset = row * inputs

                    for (column in 0 until inputs) {
                        sum += weight[offset + column] * input[column]
                    }

                    output[row] = sum
                }
            }

            private fun relu(input: FloatArray, output: FloatArray) {
                for (i in input.indices) {
                    output[i] = if (input[i] > 0f) input[i] else 0f
                }
            }

            private fun identityBatchNorm(size: Int) = BatchNormLayer(
                gamma = FloatArray(size) { 1f },
                beta = FloatArray(size),
                runningMean = FloatArray(size),
                runningVar = FloatArray(size) { 1f - BATCH_NORM_EPSILON }
            )
        }
    }

}
