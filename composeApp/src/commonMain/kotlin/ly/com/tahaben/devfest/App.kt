package ly.com.tahaben.devfest

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import devfesttripoli2024.composeapp.generated.resources.Res
import devfesttripoli2024.composeapp.generated.resources.ibtissem
import devfesttripoli2024.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun App() {
    val speakersList = listOf(
        Person(
            name = "Ibtissem Hattab",
            title = "DevOps Engineer at InstaDeep\n Google Cloud GDE,",
            picture = Res.drawable.ibtissem,
            bio = "DevOps Engineer at Instadeep\nOrganizer at GDG Sousse\nAmbassador at WTM Sousse",
            xLink = "https://twitter.com/IbtissemHattab"
        ),
        Person(
            name = "Mohamed Bashir",
            title = "Almadar Aljadid\nTelco Cloud Engineer",
            picture = Res.drawable.mohammed,
            bio = "Telco Cloud Engineer with extensive experience in building and managing cloud infrastructure. Skilled in designing scalable, resilient cloud environments for telecom, with a focus on performance and seamless integration with emerging technologies. "
        ),
        Person(
            name = "Abdullah Arab",
            title = "Software engineer.",
            picture = Res.drawable.abdullah,
            bio = "Skilled software engineer specializing in mobile development with Flutter. Passionate about building high-performance apps and leading successful projects."
        ),
        Person(
            name = "Sondos Mohammed",
            title = "Web developer.",
            picture = Res.drawable.sondos,
            bio = null
        ),
        Person(
            name = "Taha Ben Ashur",
            title = "GDG Tripoli\nKotlin / Android developer - Instructor / GDG Tripoli organizer",
            picture = Res.drawable.taha,
            bio = "Freelance developer and instructor with over 4 years of experience across various domains. Passionate about contributing to open-source projects and sharing my knowledge through Kotlin tutorials on my blog, giving back to the community that has helped me grow.",
            xLink = "https://twitter.com/tahaak67"
        )
    )
    val organizersList = listOf(
        Person(
            name = "Ahmed Elbaloug",
            title = "Organizer",
            picture = Res.drawable.ahmed_elbaloug
        ),
        Person(
            name = "Souad Elrayes",
            title = "Organizer",
            picture = Res.drawable.souad_elrayes
        ),
        Person(
            name = "Abdullah Bouzid",
            title = "Organizer",
            picture = Res.drawable.abdullah_bouzid
        ),
        Person(
            name = "علي يسري غومه",
            title = "Organizer",
            picture = Res.drawable.ali_yousri
        ),
        Person(
            name = "Mohamed Fathi",
            title = "Organizer",
            picture = Res.drawable.mohamed_fathi
        ),
        Person(
            name = "Mohamed Algaramanly",
            title = "Organizer",
            picture = Res.drawable.mohamed_algaramanly
        ),
        Person(
            name = "Malik Mamlok",
            title = "Organizer",
            picture = Res.drawable.malik_mamlok
        ),
        Person(
            name = "Abdulrahman Jerbi",
            title = "Organizer",
            picture = Res.drawable.abdulrahman_jerbi
        ),
    )
    val hostPerson = Person(
        name = "Montaha M.Sherif",
        title = "GDG Tripoli",
        bio = "Hello\n\uD83D\uDC69\u200D\uD83C\uDF93 Current Student at the College of Electronic Technology, 7th semester in the Communication Department.\n\n\uD83C\uDF10 Networking Enthusiast with a passion for all things related to networking! I've completed CCNA and CCNP courses and have hands-on experience with various CCNA projects.\nRecently, l've delved into\n\uD83D\uDC27Linux and consider myself an intermediate user, particularly with Red Hat. I'm also learning Bash and Python\uD83D\uDC0D\n\uD83D\uDCC8Excited to share that I'm skilled in installing and configuring Grafana and Zabbix for monitoring and visualization.",
        picture = Res.drawable.montaha
    )

    MaterialTheme {
        GDGTripoliScreen(
            speakers = speakersList,
            hostPerson = hostPerson,
            organizers = organizersList
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GDGTripoliScreen(speakers: List<Person>, hostPerson: Person, organizers: List<Person>) {
    val expandedId = remember { mutableStateOf("") }
    val currentBio = remember { mutableStateOf<String?>(null) }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy((16).dp),
        contentPadding = PaddingValues(vertical = 50.dp),
        columns = GridCells.Adaptive(minSize = 350.dp)
    ) {
        stickyHeader {
            Header(text = "Speakers")
        }

        items(speakers) { speaker ->
            SpeakerItem(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                isExpanded = expandedId.value == speaker.name,
                onItemClick = { expandedId.value = speaker.name },
                person = speaker,
                onShowBioClick = { bio ->
                    currentBio.value = bio
                }
            )
        }

        stickyHeader {
            Header(text = "Host")
        }

        item {
            SpeakerItem(
                modifier = Modifier.fillMaxWidth(),
                isExpanded = expandedId.value == hostPerson.name,
                person = hostPerson,
                onItemClick = { expandedId.value = hostPerson.name },
                onShowBioClick = {
                    currentBio.value = it
                }
            )
        }
        stickyHeader {
            Header(text = "Partner")
        }
        item {
            Image(
                painter = painterResource(Res.drawable.jetbrains_logo),
                contentDescription = "jetbrains logo"
            )
        }
        stickyHeader {
            Header(text = "Organizers")
        }
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(
                    32.dp,
                    alignment = Alignment.CenterHorizontally
                ), verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                organizers.forEach { organizer ->
                    SpeakerItem(
                        modifier = Modifier.wrapContentHeight(),
                        isExpanded = false,
                        onItemClick = { },
                        person = organizer,
                        onShowBioClick = {}
                    )
                }
            }
        }
    }
    AnimatedVisibility(
        visible = !currentBio.value.isNullOrEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BioOverlay(bio = currentBio.value, onCloseClick = { currentBio.value = "" })
    }
}

@Composable
fun BioOverlay(modifier: Modifier = Modifier, bio: String?, onCloseClick: () -> Unit) {
    Box(
        Modifier.fillMaxSize()
            .background(Color.Black.copy(alpha = 0.80f))
            .padding(64.dp)
    ) {
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onCloseClick
        ) {
            Icon(Icons.Default.Close, contentDescription = "close bio", tint = Color.White)
        }
        Text("$bio", color = Color.White, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Header(modifier: Modifier = Modifier, text: String) {
    Surface(modifier = modifier) {
        Text(text = text, fontSize = 35.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun SpeakerItem(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    person: Person,
    onItemClick: () -> Unit,
    onShowBioClick: (String) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .size(130.dp)
                        .clip(CircleShape)
                        .clickable {
                            onItemClick()
                        },
                    painter = painterResource(resource = person.picture),
                    contentDescription = "pic"
                )
                if (person.xLink != null) {
                    Image(
                        painter = painterResource(Res.drawable.x),
                        contentDescription = "X",
                        colorFilter = ColorFilter.tint(Color(0xFF4285F4)),
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.BottomCenter)
                            .clip(CircleShape)
                            .clickable {
                                uriHandler.openUri(person.xLink)
                            }
                            .background(Color.White)
                            .padding(8.dp)
                    )
                }
            }
            Text(person.name, fontWeight = FontWeight.Bold)
        }

        AnimatedVisibility(isExpanded) {
            Column {
                Row {
                    Spacer(Modifier.width(16.dp).background(Color.Yellow))
                    Text(person.title)
                }

                if (person.bio != null) {
                    TextButton(onClick = { onShowBioClick(person.bio) }) {
                        Text("Bio")
                    }
                }
            }
        }
    }
}

data class Person(
    val name: String,
    val title: String,
    val bio: String? = null,
    val picture: DrawableResource,
    val xLink: String? = null
)