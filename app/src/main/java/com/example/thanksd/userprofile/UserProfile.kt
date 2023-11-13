package com.example.thanksd.userprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thanksd.R

class UserProfile {
    @Composable
    fun UserInfo(){
        BoxWithConstraints(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(15.dp, 30.dp, 15.dp, 10.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(30.dp) // 간격 30dp
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ){
                    userData()
                }
                Account()
                Notification()
                Extra()
            }
        }
    }

    @Composable
    fun userData(){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            /* user image 코드 필요 */
            //TODO 유저 이미지 불러오기
            Spacer(
                modifier = Modifier.width(10.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ){
                Text(
                    text = "유저 닉네임",
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp)
                Text(
                    text = "유저 이메일",
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    color = Color.Gray
                )

            }

        }
    }

    @Composable
    fun Account(){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.user_account),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Change Name",
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    //TODO 클릭시 기능 구현
                }
            )
            Text(
                text = "Language",
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    //TODO 클릭시 기능 구현
                }
            )
        }
    }

    @Composable
    fun Notification(){
        var isMessageEnabled by remember { mutableStateOf(false) }
        var isSoundEnabled by remember { mutableStateOf(false) }
        var isVibrateEnabled by remember { mutableStateOf(false) }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_notification),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(10.dp))

            //message 설정
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Message Alerts",
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1.0f)
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Switch(
                    checked = isMessageEnabled,
                    modifier = Modifier.scale(0.6f),
                    onCheckedChange = { isChecked ->
                        isMessageEnabled = isChecked
                        if (isChecked) {
                            // 알림 설정
                            //TODO 알림 설정 기능
//                    enableNotification(context)
                        } else {
                            // 알림 해제
//                    disableNotification(context)
                        }
                    }
                )
            }

            //Sound 설정
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Sound",
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1.0f)
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Switch(
                    checked = isSoundEnabled,
                    modifier = Modifier.scale(0.6f),
                    onCheckedChange = { isChecked ->
                        isSoundEnabled = isChecked
                        if (isChecked) {
                            // 알림 설정
//                    enableNotification(context)
                        } else {
                            // 알림 해제
//                    disableNotification(context)
                        }
                    }
                )
            }

            /* 진동 설정 */
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Vibrate",
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1.0f)
                )
                Spacer(modifier = Modifier.weight(1.0f))
                Switch(
                    checked = isVibrateEnabled,
                    modifier = Modifier.scale(0.6f),
                    onCheckedChange = { isChecked ->
                        isVibrateEnabled = isChecked
                        if (isChecked) {
                            // 알림 설정
//                    enableNotification(context)
                        } else {
                            // 알림 해제
//                    disableNotification(context)
                        }
                    }
                )
            }

        }


    }

    @Composable
    fun Extra(){
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.user_etc),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Sign Out",
                textAlign = TextAlign.Start,
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier
                    .weight(1.0f)
                    .clickable {
                        //TODO 회원 탈퇴 기능 구현

                    }
            )

        }
    }



}