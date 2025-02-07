package com.example.coffee

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.coffee.ui.theme.CoffeeTheme


data class Product(
    val image: Int,
    val name: String,
    val description: String,
    val price: String,
)

private val produkts = listOf(
    Product(R.drawable.cofee_zerna,"норм кефо","не","1р"),
    Product(R.drawable.cofee_zerna,"хорошее хово","шарю","200р"),
    Product(R.drawable.cofee_zerna,"плохое кава","что","10р"),
    Product(R.drawable.cofee_zerna,"отличный кофе","тут","1000р"),
    Product(R.drawable.cofee_zerna,"такое-себе кофейка","надо","5р"),
    Product(R.drawable.cofee_zerna,"просто кофе","писать","150р"),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                MainBox()
            }


        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun MainBox() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()

    ) {

        val (topBox, midBox, botBox) = createRefs()


        TopBox(Modifier
            // .fillMaxWidth()
            //.fillMaxHeight(0.3f)
            .constrainAs(topBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(botBox.top)

            })


        BottomBox(
            Modifier
                //.fillMaxWidth()
                //.fillMaxHeight(0.7f)
                .constrainAs(botBox) {
                    top.linkTo(topBox.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)

                }
        )


        MidBox(
            Modifier
                //.fillMaxWidth()
                //.height(50.dp)
                .constrainAs(midBox) {
                    top.linkTo(topBox.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(botBox.top)
                    centerHorizontallyTo(parent)

                }
        )

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceType")
@Composable
fun TopBox(modifier: Modifier) {

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF2B2B2B), Color(0xFF000000))
    )
    val gradient2 = Brush.horizontalGradient(
        colors = listOf(Color(0xFF000000), Color(0xFF2E2E2E))
    )

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }



    Box(
        modifier = modifier
            .background(gradient)
            .fillMaxWidth()
            .fillMaxHeight(0.3F)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {


            Text(text = "Location", color = Color.Gray)// Modifier.padding(start = 10.dp,top=20.dp)
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Row {
                Text(text = "Bebrolandia", color = Color.White)
                Image(
                    painter = painterResource(id = R.drawable.draw_down),
                    contentDescription = stringResource(
                        id = R.drawable.draw_down
                    )
                )
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))

            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7F)
                        .background(gradient2, shape = RoundedCornerShape(16.dp))
                ) {

                    SearchBar(
                        query = query,
                        onQueryChange = { query = it },
                        onSearch = { /* Обработка поиска */ },
                        active = active,
                        onActiveChange = { active = it },
                        // modifier = Modifier.weight(1f),


                        placeholder = {
                            Text(
                                text = "Search coffee",
                                modifier = Modifier.alpha(0.8F)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = Color.White
                            )
                        },
                        trailingIcon = {
                            if (active) {
                                IconButton(onClick = {
                                    query = ""
                                    active = false
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Clear Query",
                                        tint = Color.White
                                    )
                                }
                            }
                        }, shape = RoundedCornerShape(10.dp),
                        colors = SearchBarDefaults.colors(
                            containerColor = Color.Transparent, // Прозрачный фон для SearchBar
                            inputFieldColors = SearchBarDefaults.inputFieldColors(
                                focusedTextColor = Color.White
                            )
                        )
                    ) {}

                }

                Spacer(modifier = Modifier.padding(start = 25.dp))

                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .background(
                            color = colorResource(id = R.color.orange),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .size(60.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ddd),
                        contentDescription = "Filter Icon",
                        tint = Color.White
                    )

                }


            }
        }

    }
}

@Composable
fun BottomBox(modifier: Modifier) {

    Box(

        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .fillMaxHeight(0.7F)
            .padding(top = 70.dp)
    ) {

        var selectedItem by remember {
            mutableStateOf<String?>(null)
        }

        val items = listOf("All Coffee", "Machiato", "Latte", "Americano")

        LazyRow {
            items(items) { item ->
                Text(
                    text = item,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedItem == item) Color.White else Color.Black,

                    modifier = Modifier
                        .padding(16.dp)
                        .background(
                            color = if (selectedItem == item) colorResource(id = R.color.brown) else colorResource(
                                id = R.color.grey
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { selectedItem = item }
                        .padding(8.dp)
                )
            }
        }
        LazyColumnMain(produkts)


    }

}

@Composable
fun CoffeeCard(product: Product) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(1.dp))

            )

            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 25.sp)
            Text(
                text = product.description,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.grey)
            )

            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = product.price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.weight(0.8F)
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .weight(0.3F)
                        .background(
                            colorResource(id = R.color.orange),
                            shape = RoundedCornerShape(15.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = null,
                        tint = Color.White
                    )
                }

            }

        }
    }
}

@Composable
fun LazyColumnMain(produkts: List<Product>){
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ){
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
            contentPadding = PaddingValues(16.dp)
        ){
            items(produkts){product->
                CoffeeCard(product = product)

            }
        }
    }
}

@Composable
fun MidBox(modifier: Modifier) {

    Box(


        modifier = modifier
            .background(colorResource(id = R.color.brown), shape = RoundedCornerShape(15.dp))
            .fillMaxWidth(0.9F)
            .height(140.dp)


    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.6F)) {


                Text(
                    text = "Location",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Red, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 5.dp, vertical = 2.dp)

                )
                Spacer(modifier = Modifier.padding(top = 8.dp))


                Text(
                    text = "Buy one get",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Black)
                )
                Spacer(modifier = Modifier.padding(top = 8.dp))


                Text(
                    text = "one FREE",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Black)
                )

            }
            Column(modifier = Modifier.fillMaxWidth(1F)) {
                Image(

                    painter = painterResource(id = R.drawable.cofee_zerna),
                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp)


                )
            }

        }


    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeTheme {
        Greeting("Android")
    }
}
