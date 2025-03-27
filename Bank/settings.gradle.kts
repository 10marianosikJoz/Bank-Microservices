plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    id ("org.jetbrains.kotlin.jvm") version "1.9.25" apply false
}
rootProject.name = "Bank"
include("account")
include("loan")
include("card")
include("config-server")
include("service-discovery")
include("api-gateway")
include("notification")