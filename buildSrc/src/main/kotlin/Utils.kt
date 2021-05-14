import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*
import java.util.function.BiConsumer

object Utils {
    fun loadOssrhConfig(root: Project, consumer: BiConsumer<String, String>) {
        val ossrhUsernameKey = "ossrhUsername"
        val ossrhPasswordKey = "ossrhPassword"
        val file = File(root.projectDir.path + "/local.properties")
        println(file.absolutePath)
        val properties: Properties
        if (file.exists()) {
            properties = Properties()
            properties.load(FileInputStream(file))
            println("从 local.properties 中加载ossrh账号密码.")
        } else {
            properties = System.getProperties()
            println("从 System 中加载ossrh账号密码.")
        }
        val ossrhUsername = properties.getProperty(ossrhUsernameKey)
        val ossrhPassword = properties.getProperty(ossrhPasswordKey)
        consumer.accept(ossrhUsername ?: "", ossrhPassword ?: "")
    }
}