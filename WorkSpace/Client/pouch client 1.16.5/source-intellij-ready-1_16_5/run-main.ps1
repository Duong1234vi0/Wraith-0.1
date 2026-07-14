$ErrorActionPreference = "Stop"

$ProjectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$Java = "C:\Program Files\Java\jdk-21\bin\java.exe"
$Jar = Join-Path $ProjectRoot "libs\1_16_5_decrypted.jar"
$RuntimeStubsJar = Join-Path $ProjectRoot "libs\runtime-stubs.jar"
$ResourcesDir = Join-Path $ProjectRoot "src\main\resources"
$RunDir = Join-Path $ProjectRoot "run"
$NativesDir = Join-Path $RunDir "natives"
$VanillaAssetsDir = Join-Path $RunDir "vanilla-assets"
$MinecraftDir = Join-Path $env:APPDATA ".minecraft"
$LibraryDir = Join-Path $MinecraftDir "libraries"
$VanillaClientJar = Join-Path $LibraryDir "v1\objects\37fd3c903861eeff3bc24b71eed48f828b5269c8\client.jar"
$AssetsDir = Join-Path $MinecraftDir "assets"
$AssetIndex = Join-Path $AssetsDir "indexes\1.16.json"

if (-not (Test-Path -LiteralPath $AssetsDir)) {
    $AssetsDir = Join-Path $ProjectRoot "src\main\resources\assets"
}

if (-not (Test-Path -LiteralPath $Java)) {
    throw "Java 21 was not found at $Java"
}

if (-not (Test-Path -LiteralPath $LibraryDir)) {
    throw "Minecraft libraries folder was not found at $LibraryDir"
}

New-Item -ItemType Directory -Force -Path $RunDir | Out-Null
New-Item -ItemType Directory -Force -Path $NativesDir | Out-Null
New-Item -ItemType Directory -Force -Path $VanillaAssetsDir | Out-Null

function Resolve-LibraryJar {
    param([string]$RelativePath)

    $Path = Join-Path $LibraryDir $RelativePath
    if (Test-Path -LiteralPath $Path) {
        return (Resolve-Path -LiteralPath $Path).Path
    }

    Write-Warning "Library was not found: $RelativePath"
    return $null
}

$PreferredLibraries = @(
    "com\mojang\patchy\1.3.9\patchy-1.3.9.jar",
    "oshi-project\oshi-core\1.1\oshi-core-1.1.jar",
    "net\java\dev\jna\jna\4.4.0\jna-4.4.0.jar",
    "net\java\dev\jna\platform\3.4.0\platform-3.4.0.jar",
    "com\ibm\icu\icu4j\66.1\icu4j-66.1.jar",
    "com\mojang\javabridge\1.0.22\javabridge-1.0.22.jar",
    "net\sf\jopt-simple\jopt-simple\5.0.3\jopt-simple-5.0.3.jar",
    "io\netty\netty-all\4.1.25.Final\netty-all-4.1.25.Final.jar",
    "com\google\guava\guava\21.0\guava-21.0.jar",
    "org\apache\commons\commons-lang3\3.5\commons-lang3-3.5.jar",
    "commons-io\commons-io\2.5\commons-io-2.5.jar",
    "commons-codec\commons-codec\1.10\commons-codec-1.10.jar",
    "com\mojang\brigadier\1.0.17\brigadier-1.0.17.jar",
    "com\mojang\datafixerupper\4.0.26\datafixerupper-4.0.26.jar",
    "com\google\code\gson\gson\2.8.0\gson-2.8.0.jar",
    "com\mojang\authlib\2.1.28\authlib-2.1.28.jar",
    "org\apache\commons\commons-compress\1.8.1\commons-compress-1.8.1.jar",
    "org\apache\httpcomponents\httpclient\4.3.3\httpclient-4.3.3.jar",
    "commons-logging\commons-logging\1.1.3\commons-logging-1.1.3.jar",
    "org\apache\httpcomponents\httpcore\4.3.2\httpcore-4.3.2.jar",
    "it\unimi\dsi\fastutil\8.2.1\fastutil-8.2.1.jar",
    "org\apache\logging\log4j\log4j-api\2.8.1\log4j-api-2.8.1.jar",
    "org\apache\logging\log4j\log4j-core\2.8.1\log4j-core-2.8.1.jar",
    "org\lwjgl\lwjgl\3.2.2\lwjgl-3.2.2.jar",
    "org\lwjgl\lwjgl-glfw\3.2.2\lwjgl-glfw-3.2.2.jar",
    "org\lwjgl\lwjgl-jemalloc\3.2.2\lwjgl-jemalloc-3.2.2.jar",
    "org\lwjgl\lwjgl-openal\3.2.2\lwjgl-openal-3.2.2.jar",
    "org\lwjgl\lwjgl-opengl\3.2.2\lwjgl-opengl-3.2.2.jar",
    "org\lwjgl\lwjgl-stb\3.2.2\lwjgl-stb-3.2.2.jar",
    "org\lwjgl\lwjgl-tinyfd\3.2.2\lwjgl-tinyfd-3.2.2.jar",
    "com\mojang\text2speech\1.11.3\text2speech-1.11.3.jar",
    "org\tlauncher\tl_skin_cape_1.16.5\1.37d\tl_skin_cape_1.16.5-1.37d.jar"
)

$NativeLibraries = @(
    "org\lwjgl\lwjgl\3.2.2\lwjgl-3.2.2-natives-windows.jar",
    "org\lwjgl\lwjgl-glfw\3.2.2\lwjgl-glfw-3.2.2-natives-windows.jar",
    "org\lwjgl\lwjgl-jemalloc\3.2.2\lwjgl-jemalloc-3.2.2-natives-windows.jar",
    "org\lwjgl\lwjgl-openal\3.2.2\lwjgl-openal-3.2.2-natives-windows.jar",
    "org\lwjgl\lwjgl-opengl\3.2.2\lwjgl-opengl-3.2.2-natives-windows.jar",
    "org\lwjgl\lwjgl-stb\3.2.2\lwjgl-stb-3.2.2-natives-windows.jar",
    "org\lwjgl\lwjgl-tinyfd\3.2.2\lwjgl-tinyfd-3.2.2-natives-windows.jar",
    "com\mojang\text2speech\1.11.3\text2speech-1.11.3-natives-windows.jar"
)

foreach ($Native in $NativeLibraries) {
    $NativeJar = Resolve-LibraryJar $Native
    if ($NativeJar) {
        $TempNativeZip = Join-Path $RunDir "_native.zip"
        Copy-Item -LiteralPath $NativeJar -Destination $TempNativeZip -Force
        Expand-Archive -LiteralPath $TempNativeZip -DestinationPath $NativesDir -Force
        Remove-Item -LiteralPath $TempNativeZip -Force
    }
}

$LibraryJars = $PreferredLibraries |
    ForEach-Object { Resolve-LibraryJar $_ } |
    Where-Object { $_ }

function Sync-VanillaAssets {
    if (-not (Test-Path -LiteralPath $AssetIndex)) {
        Write-Warning "Minecraft 1.16 asset index was not found at $AssetIndex"
        return
    }

    $Marker = Join-Path $VanillaAssetsDir ".assets-1.16-ready"
    if (Test-Path -LiteralPath $Marker) {
        return
    }

    $Index = Get-Content -LiteralPath $AssetIndex -Raw | ConvertFrom-Json
    foreach ($Property in $Index.objects.PSObject.Properties) {
        $Name = $Property.Name
        $Hash = $Property.Value.hash
        $Source = Join-Path $AssetsDir ("objects\" + $Hash.Substring(0, 2) + "\" + $Hash)
        if (-not (Test-Path -LiteralPath $Source)) {
            continue
        }

        if ($Name -eq "pack.mcmeta") {
            $Destination = Join-Path $VanillaAssetsDir $Name
        }
        else {
            $Destination = Join-Path $VanillaAssetsDir ("assets\" + $Name)
        }

        $DestinationDir = Split-Path -Parent $Destination
        New-Item -ItemType Directory -Force -Path $DestinationDir | Out-Null
        Copy-Item -LiteralPath $Source -Destination $Destination -Force
    }

    New-Item -ItemType Directory -Force -Path (Join-Path $VanillaAssetsDir "assets") | Out-Null
    New-Item -ItemType Directory -Force -Path (Join-Path $VanillaAssetsDir "data") | Out-Null
    New-Item -ItemType File -Force -Path (Join-Path $VanillaAssetsDir "assets\.mcassetsroot") | Out-Null
    New-Item -ItemType File -Force -Path (Join-Path $VanillaAssetsDir "data\.mcassetsroot") | Out-Null
    New-Item -ItemType File -Force -Path $Marker | Out-Null
}

Sync-VanillaAssets

$EnglishLang = Join-Path $VanillaAssetsDir "assets\minecraft\lang\en_us.json"
if (-not (Test-Path -LiteralPath $EnglishLang)) {
    foreach ($FallbackIndex in @("1.21.json", "19.json", "1.8.json")) {
        $FallbackIndexPath = Join-Path $AssetsDir ("indexes\" + $FallbackIndex)
        if (-not (Test-Path -LiteralPath $FallbackIndexPath)) {
            continue
        }

        $FallbackObjects = Get-Content -LiteralPath $FallbackIndexPath -Raw | ConvertFrom-Json
        $FallbackEntry = $FallbackObjects.objects.PSObject.Properties["minecraft/lang/en_us.json"]
        if ($FallbackEntry -eq $null) {
            continue
        }

        $Hash = $FallbackEntry.Value.hash
        $Source = Join-Path $AssetsDir ("objects\" + $Hash.Substring(0, 2) + "\" + $Hash)
        if (Test-Path -LiteralPath $Source) {
            New-Item -ItemType Directory -Force -Path (Split-Path -Parent $EnglishLang) | Out-Null
            Copy-Item -LiteralPath $Source -Destination $EnglishLang -Force
            break
        }
    }
}

$ClientJars = @()
if (Test-Path -LiteralPath $RuntimeStubsJar) {
    $ClientJars += $RuntimeStubsJar
}
$ClientJars += $Jar
if (Test-Path -LiteralPath $VanillaClientJar) {
    $ClientJars += $VanillaClientJar
}

$ClassPath = (@($VanillaAssetsDir, $ResourcesDir) + $ClientJars + $LibraryJars) -join [IO.Path]::PathSeparator
$ArgFile = Join-Path $RunDir "main.args"

Push-Location $RunDir
try {
    $JavaArgs = @(
        "-Dfile.encoding=UTF-8",
        "-Djava.awt.headless=false",
        "-Djava.library.path=$NativesDir",
        "-Dorg.lwjgl.librarypath=$NativesDir",
        "-noverify",
        "-cp", $ClassPath,
        "net.minecraft.client.main.Main",
        "--version", "mcp",
        "--accessToken", "0",
        "--assetsDir", $AssetsDir,
        "--assetIndex", "1.16",
        "--userProperties", "{}"
    )

    [IO.File]::WriteAllLines($ArgFile, $JavaArgs)
    & $Java "@$ArgFile" @args
}
finally {
    Pop-Location
}
