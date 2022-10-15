/*
 * Copyright (c) 2022 dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("jvm")
}

application {
    mainClass.set("com.reposilite.plugin.mavenindexer.MavenIndexerPlugin")
}

dependencies {
    compileOnly(project(":reposilite-backend"))
    implementation("com.github.apache:maven-indexer:d6e23c1") { // TODO GH-1595 - replace with maven-indexer from maven central when it's released
        exclude("com.github.apache.maven-indexer", "commons-logging")
    }
    testImplementation(project(":reposilite-backend"))
}

tasks.withType<ShadowJar> {
    archiveFileName.set("maven-indexer-plugin.jar")
    destinationDirectory.set(file("$rootDir/reposilite-backend/src/test/workspace/plugins"))
    mergeServiceFiles()
}