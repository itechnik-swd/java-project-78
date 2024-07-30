plugins {
    id("java")
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.puppycrawl.tools/checkstyle
    implementation("com.puppycrawl.tools:checkstyle:10.17.0")

}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.17.0"
}