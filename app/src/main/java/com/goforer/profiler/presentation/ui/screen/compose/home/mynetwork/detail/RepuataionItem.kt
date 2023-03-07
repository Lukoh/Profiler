package com.goforer.profiler.presentation.ui.screen.compose.home.mynetwork.detail

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
import com.goforer.profiler.presentation.ui.theme.ColorBgSecondary
import com.goforer.profiler.presentation.ui.theme.ColorText4
import com.goforer.profiler.presentation.ui.theme.ProfilerTheme

@Composable
fun ReputationItem(
    modifier: Modifier = Modifier,
    person: Person
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier.padding(8.dp, 0.dp, 8.dp, 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(IntrinsicSize.Min)
                .background(ColorBgSecondary)
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        ) {
            Text(
                person.repuation,
                modifier = Modifier.padding(16.dp, 16.dp, 0.dp, 16.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                color = ColorText4,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode",
    showSystemUi = true
)
@Composable
fun ReputationItemPreview(modifier: Modifier = Modifier) {
    ProfilerTheme {
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = modifier.padding(8.dp, 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .height(IntrinsicSize.Min)
                    .background(ColorBgSecondary)
                    .wrapContentHeight(Alignment.Top)
                    .fillMaxWidth()
                    .heightIn(min = 56.dp)
            ) {
                val person = Person(0,"LLyyiok", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4", "sociable & gregarious", "+820101111-1111","", "Mar, 04, 1999","Lukoh is a tremendously capable and dedicated mobile SW professional. He has strong analytical and innovative skills which are further boosted by his solid technical background and his enthusiasm for technology. Lukoh works extremely well with colleagues, associates, and executives, adapting the analysis and communication techniques in order to accomplish the business objective. He is proficient in managing projects with consistent and successful results.\n" +
                        "I am confident that his leadership experience and expertise in SW development will make him a good SW engineer who works with many colleagues, and should come up with creative awesome ideas.\n" +
                        "He is an expert and architect in Android application development which has resulted in excellent reviews from all collegue. Lukoh is an honest and hardworking team lead, always willing to pitch in to help the team. He is efficient in planning projects, punctual in meeting deadlines, and conscientiously adheres to company standards and guidelines. On the other he understands the technical design and development, techniques and constraints. Lukoh has a true talent for communicating and negotiating where the outcome is beneficial for all involved. He is absolutely a valuable strength to any team as team lead!")

                Text(
                    person.repuation,
                    modifier = Modifier.padding(16.dp, 16.dp, 0.dp, 16.dp),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Normal,
                    color = ColorText4,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}