package com.example.cryptoapp.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.Screen
import com.example.cryptoapp.presentation.coin_detail.components.CoinTag
import com.example.cryptoapp.presentation.coin_detail.components.TeamListItem
import com.example.cryptoapp.presentation.coin_list.components.CoinListItem

@Composable
fun CoinDetailScreen(viewModel: coinDetailViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        state.coin?.let { coin ->

            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)) {

                LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
                    item {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.weight(8f),
                                //color = Color.White
                                color = colorResource(id = R.color.cielo)
                            )

                            Text(
                                text = if (coin.isActive) "active" else "inactive",
                                color = if (coin.isActive) Color.Green else Color.Red,
                                fontStyle = FontStyle.Italic,
                                textAlign = TextAlign.End,
                                modifier = Modifier
                                    .align(CenterVertically)
                                    .weight(2f)
                            )

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = coin.description, style = MaterialTheme.typography.bodyMedium, color = Color.White)

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = "Tags", fontSize = 20.sp , /*color = Color.White*/ color = colorResource(
                            id = R.color.cielo
                        ))

                        Spacer(modifier = Modifier.height(15.dp))
                        com.google.accompanist.flowlayout.FlowRow(
                            mainAxisSpacing = 10.dp,
                            crossAxisSpacing = 10.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            coin.tags.forEach { tag ->

                                CoinTag(tag = tag)
                            }

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = "Team Members", fontSize = 20.sp, /*color = Color.White*/ color = colorResource(
                            id = R.color.cielo
                        ))

                        Spacer(modifier = Modifier.height(15.dp))
                    }
                    items(coin.team) { teamMember ->

                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                        Divider()

                    }
                }


            }


        }



        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }


}