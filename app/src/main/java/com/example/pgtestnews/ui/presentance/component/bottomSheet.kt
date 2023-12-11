package com.example.pgtestnews.ui.presentance.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pgtestnews.domain.data.Article

@Composable
fun NewsBottomSheet(
    article: Article,
    onReadFullArticle:(Article) -> Unit
){
    Surface{
        Column(modifier = Modifier
            .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = article.title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.content ?: "",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            ImageHolder(url = article.urlToImage ?:"")

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = article.description ?: "",
                style = MaterialTheme.typography.labelMedium)

            Row (modifier = Modifier.fillMaxWidth()
            , verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = article.author ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold)
                Text(text = article.source.name ?: "",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.SemiBold)
            }

            Button(onClick = { onReadFullArticle(article) },
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp)) {
                Text(text = "Read Full Article")
            }
            Spacer(modifier = Modifier.height(18.dp))

        }
    }
}