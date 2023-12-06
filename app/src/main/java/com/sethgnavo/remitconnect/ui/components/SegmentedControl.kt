package com.sethgnavo.remitconnect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sethgnavo.remitconnect.ui.theme.Primary05
import com.sethgnavo.remitconnect.ui.theme.Primary100
import com.sethgnavo.remitconnect.ui.theme.Primary70

@Composable
fun SegmentedControl(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Row(
        modifier = modifier
            .selectableGroup()
            .height(48.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Primary05)
            .padding(2.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selectedOption
            val backgroundColor = if (isSelected) Primary70 else Color.Transparent
            val textColor = if (isSelected) Color.White else Primary100

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .clickable { onOptionSelected(option) }
                    .background(backgroundColor)
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSegmentedControl() {
    var selectedOption by remember { mutableStateOf("Previous recipients") }

    SegmentedControl(
        options = listOf(
            "Previous recipients",
            "New recipient"
        ),
        selectedOption = selectedOption,
        onOptionSelected = { selectedOption = it }
    )
}