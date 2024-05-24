package com.curiosity.common.components

/**
 * @author matteooriggi
 */

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.curiosity.common.fonts.Poppins
import com.curiosity.ui.theme.CuriosityGray
import com.curiosity.ui.theme.CuriosityViolet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuriosityPasswordField(
    modifier: Modifier? = null,

    text: String,
    placeholder: String = "",
    onTextChange: (String) -> Unit,
    valueColor: Color = Color.Black,
    backgroundColor: Color = Color.LightGray,
    cursorColor: Color = valueColor,
    singleLine: Boolean = true,
    fontSize: TextUnit = 24.sp,

    helperText: Boolean? = null,
    helperTextColor: Color = Color.Black,
    maxLength: Int? = null,


    ){

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column {
        TextField(
            modifier = modifier ?: Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = valueColor,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Poppins
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            textStyle = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = valueColor,
                containerColor = backgroundColor,
                cursorColor = cursorColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(10.dp),
            singleLine = singleLine,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(
                        imageVector  = icon,
                        contentDescription = description,
                        tint = valueColor
                    )
                }
            }
        )

        if(helperText != null){
            Text(
                text = if(maxLength != null) "${text.length} / $maxLength" else "${text.length}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 13.dp),
                textAlign = TextAlign.End,
                color = helperTextColor
            )
        }
    }
}


@Preview
@Composable
fun CuriosityPasswordFieldPreview(){
    var text by remember {
        mutableStateOf("")
    }
    CuriosityPasswordField(
        text = text,
        onTextChange = {
            text = it
        },
        placeholder = "Password",
        valueColor = CuriosityViolet,
        backgroundColor = CuriosityGray,
        helperText = true,
        helperTextColor = CuriosityViolet,
        maxLength = 32
    )
}