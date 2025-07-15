package k.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CharacterPager(
    currentPage: Int,
    totalPages: Int,
    onPageSelected: (Int) -> Unit,
) {
    val visiblePages = remember(currentPage, totalPages) {
        val pages = mutableListOf<Int>()
        pages.add(1)
        val start = when {
            currentPage <= 3 -> 2
            currentPage >= totalPages - 3 -> (totalPages - 4).coerceAtLeast(2)
            else -> currentPage - 1
        }
        val end = (start + 3).coerceAtMost(totalPages - 1)
        for (i in start..end) {
            if (i != 1 && i != totalPages) {
                pages.add(i)
            }
        }
        if (!pages.contains(totalPages)) pages.add(totalPages)
        pages
    }

    Row(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(0.2f),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        visiblePages.forEach { page ->
            val isSelected = page == currentPage
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background,
                    )
                    .clickable { onPageSelected(page) },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = page.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}
