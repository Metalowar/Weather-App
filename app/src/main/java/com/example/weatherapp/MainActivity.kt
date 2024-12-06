package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.BlueJC
import com.example.weatherapp.ui.theme.PastelBlue
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.BuildConfig
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import android.content.Context
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.ui.platform.LocalContext


// Основна активність додатку, яка відображає головний екран користувачеві.
// Відповідає за виклик WeatherScreen та управління UI.

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels() // Ініціалізація ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                NavGraph(viewModel = viewModel)  // Передаємо ViewModel у NavGraph
            }
        }
    }
}


@Composable
fun NavGraph(startDestination: String = "weather", viewModel: WeatherViewModel) {
    val navController = rememberNavController()

    // Set up the navigation host with the start destination
    NavHost(navController, startDestination = startDestination) {
        composable("weather") {
            WeatherScreen(navController, viewModel) // Передаємо viewModel в WeatherScreen
        }
        composable("screen_two") {
            ScreenTwoScreen(navController, viewModel) // Передаємо viewModel в ScreenTwoScreen
        }
    }
}

// Detailed Weather screen
@Composable
fun ScreenTwoScreen(navController: NavController?, viewModel: WeatherViewModel, modifier: Modifier = Modifier) {
    // Collect the weather data and forecast data state
    val weatherData by viewModel.weatherData.collectAsState()
    val forecastData by viewModel.forecastData.collectAsState()
    // Create a Box layout to fill the screen and center its content
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // Choose the appropriate background based on the weather description
        val weatherDescription = weatherData?.weather?.firstOrNull()?.description
        val background = when {
            weatherDescription?.contains("clear sky", ignoreCase = true) == true -> painterResource(R.drawable.sun_3588618_1280)
            weatherDescription?.contains("rain", ignoreCase = true) == true -> painterResource(R.drawable.r__1_)
            weatherDescription?.contains("snow", ignoreCase = true) == true -> painterResource(R.drawable.oip)
            weatherDescription?.contains("mist", ignoreCase = true) == true -> painterResource(R.drawable.oip__1_)
            weatherDescription?.contains("few clouds", ignoreCase = true) == true -> painterResource(R.drawable.r)
            weatherDescription?.contains("scattered clouds", ignoreCase = true) == true -> painterResource(R.drawable.scattered_white_clouds_b19839264934a8d79dd4417668d701ff)
            weatherDescription?.contains("broken clouds", ignoreCase = true) == true -> painterResource(R.drawable.broken_clouds_by_kevintheman_dax9bd4)
            weatherDescription?.contains("broken clouds", ignoreCase = true) == true -> painterResource(R.drawable.broken_clouds_by_kevintheman_dax9bd4)
            weatherDescription?.contains("overcast clouds", ignoreCase = true) == true -> painterResource(R.drawable.broken_clouds_by_kevintheman_dax9bd4)
            else -> painterResource(R.drawable.app_templ)
        }

        // Display the background image
        Image(
            painter = background,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Create a Column layout to arrange items vertically
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // Check if weatherData and forecastData are not null
            weatherData?.let { data ->
                forecastData?.main?.temp?.minus(273.15)?.let { celsiusTemp ->
                    // Display the city name
                    Text(
                        text = data.name,
                        color = Color.White,
                        fontSize = 48.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(4f, 4f),
                                blurRadius = 4f
                            )
                        )
                    )
                    // Add space between text elements
                    Spacer(modifier = Modifier.height(32.dp))
                    // Display the temperature
                    Text(
                        text = "${String.format("%.2f", celsiusTemp)} °C",
                        color = Color.White,
                        fontSize = 86.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(4f, 4f),
                                blurRadius = 4f
                            )
                        )
                    )
                    // Add space between text elements
                    Spacer(modifier = Modifier.height(32.dp))
                    // Display the humidity
                    Text(
                        text = "Humidity: ${data.main.humidity} %",
                        color = Color.White,
                        fontSize = 32.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(4f, 4f),
                                blurRadius = 4f
                            )
                        )
                    )
                    // Add space between text elements
                    Spacer(modifier = Modifier.height(32.dp))
                    // Display the weather description
                    Text(
                        text = data.weather.firstOrNull()?.description.orEmpty(),
                        color = Color.White,
                        fontSize = 32.sp,
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                offset = Offset(4f, 4f),
                                blurRadius = 4f
                            )
                        )
                    )
                }
            } ?: run {
                // Display loading text if weather data is not available
                Text("Loading weather data...", color = Color.White)
            }

            // Add space between the text and the button
            Spacer(modifier = Modifier.height(32.dp))
            // Display the "Back to Weather" button
            Button(
                onClick = { navController?.navigate("weather") },
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Back to Weather", color = Color.White)
            }
        }
    }
}




//    Головний Composable для відображення екрану пошуку міст.
//    Містить поле введення для введення назви міста та кнопку для виконання пошуку.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController?, viewModel: WeatherViewModel, context: Context) {
    val cities by viewModel.cities.collectAsState()
    val recentCities by viewModel.recentCities.collectAsState()
    var city by remember { mutableStateOf("") }
    var showRecentDialog by remember { mutableStateOf(false) }
    val apiKey = BuildConfig.API_KEY

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.app_templ),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Іконка для перегляду списку нещодавно відвіданих міст
            IconButton(onClick = { showRecentDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Refresh, // Іконка "історія"
                    contentDescription = "Недавні міста"
                )
            }

            // Поле для введення назви міста
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Введіть місто") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.findCities(city) },
                colors = ButtonDefaults.buttonColors(BlueJC)
            ) {
                Text(text = "Пошук")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (cities.isNotEmpty()) {
                LazyColumn {
                    items(cities) { city ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.addCityToRecent(context, city.name) // Додати до списку
                                    navController?.navigate("screen_two")
                                }
                        ) {
                            Text(text = city.name)
                        }
                    }
                }
            }

            // Діалог із нещодавно відвіданими містами
            if (showRecentDialog) {
                AlertDialog(
                    onDismissRequest = { showRecentDialog = false },
                    title = { Text("Нещодавно відвідані міста") },
                    text = {
                        LazyColumn {
                            items(recentCities) { city ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = city)
                                    IconButton(
                                        onClick = { viewModel.removeCityFromRecent(context, city) }
                                    ) {
                                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Видалити")
                                    }
                                }
                            }
                        }
                    },
                    confirmButton = {
                        Button(onClick = { showRecentDialog = false }) {
                            Text("Закрити")
                        }
                    }
                )
            }
        }
    }
}


//     Прев'ю компонента WeatherScreen для перегляду в режимі розробки.
