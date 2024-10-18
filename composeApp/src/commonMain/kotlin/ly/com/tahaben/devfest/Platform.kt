package ly.com.tahaben.devfest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform