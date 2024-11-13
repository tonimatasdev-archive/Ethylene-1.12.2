plugins {
    java
    id("com.gtnewhorizons.retrofuturagradle") version "1.3.35"
    id("idea")
    id("eclipse")
}

val modVersion: String by extra
val minecraftVersion: String by extra

version = "$modVersion-$minecraftVersion"
group = "dev.tonimatas"

java {
    withSourcesJar()
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

minecraft {
    mcVersion.set("1.12.2")

    mcpMappingChannel.set("stable")
    mcpMappingVersion.set("39")

    useDependencyAccessTransformers.set(true)
}

tasks.deobfuscateMergedJarToSrg.configure {
    accessTransformerFiles.from("src/main/resources/META-INF/ethylene.cfg")
}

sourceSets.main.get().resources { srcDir("src/generated/resources") }

repositories {
    maven(url = "https://maven.cleanroommc.com")
}

dependencies {
    // Bukkit Libs
    implementation("commons-lang:commons-lang:2.6")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    
    implementation("org.yaml:snakeyaml:1.19")
    
    // CraftBukkit
    implementation("jline:jline:2.12.1")
    
    val mixin = modUtils.enableMixins("zone.rong:mixinbooter:10.1", "ethylene.mixins.refmap.json") as String
    
    api(mixin) {
        isTransitive = false
    }
    
    annotationProcessor("org.ow2.asm:asm-debug-all:5.2")
    annotationProcessor("com.google.guava:guava:24.1.1-jre")
    annotationProcessor("com.google.code.gson:gson:2.8.6")
    annotationProcessor(mixin) {
        isTransitive = false
    }
}

tasks.processResources {
    val replaceProperties = mapOf("modVersion" to modVersion, "minecraftVersion" to minecraftVersion)

    inputs.properties(replaceProperties)

    filesMatching(listOf("mcmod.info", "pack.mcmeta")) {
        expand(replaceProperties)
    }
}

tasks.jar {
    manifest {
        attributes(
            "Manifest-Version" to "1.0",
        )
    }

    finalizedBy("reobfJar")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
