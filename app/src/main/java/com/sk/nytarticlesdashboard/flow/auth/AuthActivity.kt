package com.sk.nytarticlesdashboard.flow.auth

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.nytarticlesdashboard.R
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.base.LocalHelper
import com.sk.nytarticlesdashboard.flow.home.HomeActivity
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {




    private val viewModel: AuthViewModel by viewModels()

    val tabs =
        listOf(
            TabItem(
                title = "Login",
                screen = { LoginScreen(viewModel = viewModel) }
            ),
            TabItem(
                title = "Register",
                screen = { RegisterScreen(viewModel =viewModel) }
            ),
        )

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocalHelper.setLocale(newBase, AppPrefrencesHelper.getPreferredLanguage(newBase))
        )
    }
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()
            var titleState by remember {
                mutableStateOf("")
            }
            NYTArticlesDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { CenterAlignedTopAppBar(title = { Text(text = titleState)}, colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                            containerColor = colorScheme.primary,
                            titleContentColor = colorScheme.onPrimary
                        )) },
                        content = {
                            Column (
                                modifier = Modifier.padding(it)
                            ) {
                                TabRow(
                                    selectedTabIndex = pagerState.currentPage,
                                    modifier = Modifier.padding(end = 100.dp)
                                ) {
                                    tabs.forEachIndexed { index, item ->
                                        Tab(
                                            selected = index == pagerState.currentPage,
                                            text = { Text(text = item.title) },
                                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                                        )
                                    }
                                }
                                HorizontalPager(
                                    pageCount = tabs.size,
                                    state = pagerState
                                ) {
                                    tabs[pagerState.currentPage].screen()
                                    titleState = tabs[pagerState.currentPage].title
                                }
                            }
                        })
                }
            }
        }
    }

}



@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
viewModel: AuthViewModel
) {

    val mContext = LocalContext.current
    if(viewModel.authState){
        val intent = Intent(mContext, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        mContext.startActivity(intent)
    }else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                painter = painterResource(id = R.drawable.new_york_times_t_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 120.dp, 120.dp)
                    .padding(top = 20.dp)
            )
            OutlinedTextField(value = viewModel.loginEmailState, onValueChange = {
                viewModel.loginEmailState = it
            }, isError = viewModel.loginEmailStateError,
                supportingText = {
                    if(viewModel.loginEmailStateError){
                        Text(text = stringResource(id = R.string.this_field_must_not_be_empty))
                    }
                },
                label = { Text(text = stringResource(id = R.string.email)) }, modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(value = viewModel.loginPasswordState,
                onValueChange = {
                    viewModel.loginPasswordState = it
                },
                isError = viewModel.loginPasswordStateError,
                supportingText = {
                if(viewModel.loginPasswordStateError){
                    Text(text = stringResource(id = R.string.this_field_must_not_be_empty))
                }
                },
                label = { Text(text = stringResource(id = R.string.password)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth(), visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {

                    viewModel.login()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
            ) {

                Text(text = stringResource(id = R.string.login))

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
viewModel: AuthViewModel
) {
    val mContext = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.new_york_times_t_icon),
            contentDescription = null,
            modifier = Modifier
                .size(width = 120.dp, 120.dp)
                .padding(top = 20.dp)
        )
        OutlinedTextField(value = viewModel.registerFullNameState, onValueChange = {
            viewModel.registerFullNameState =it
        }, label = { Text(text =stringResource(id = R.string.full_name)) }, isError = viewModel.registerFullNameStateError,
            supportingText = {
                if(viewModel.registerFullNameStateError) {
                    Text(text = stringResource(id = R.string.this_field_must_not_be_empty))
                }
            }, modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(value = viewModel.registerEmailState, onValueChange = {
            viewModel.registerEmailState = it
        }, label = { Text(text = stringResource(id = R.string.email)) } , isError = viewModel.registerEmailStateError,
            supportingText = {
                if(viewModel.registerEmailStateError) {
                    Text(text = stringResource(id = R.string.this_field_must_not_be_empty))
                }
            },modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(value = viewModel.registerNationalIDState, onValueChange = {
            viewModel.registerNationalIDState= it
        }, label = { Text(text = stringResource(id = R.string.national_id)) }, isError = viewModel.registerNationalIDStateError,
            supportingText = {
                if(viewModel.registerNationalIDStateError) {
                    Text(text = stringResource(id = R.string.this_field_must_not_be_empty))
                }
            },modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )

        OutlinedTextField(value =viewModel.registerPhoneState, onValueChange = {
            viewModel.registerPhoneState = it
        }, label = { Text(text = stringResource(id = R.string.phone_number)) }, isError = viewModel.registerPhoneStateError,
            supportingText = {
                if(viewModel.registerPhoneStateError) {
                    Text(text = stringResource(id = R.string.this_field_must_not_be_empty))
                }
            }, modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )

        OutlinedButton(
            onClick = {

                      showDatePicker(mContext,viewModel);

                      },
            contentPadding = PaddingValues(17.dp),

            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()

        ) {

            if(viewModel.registerDateOfBirthStateError){
                Text(
                    text = stringResource(id = R.string.date_of_birth_must_be_selected),
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth()

                )
            }else {
                Text(
                    text = viewModel.registerDateOfBirthState,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        OutlinedTextField(value = viewModel.registerPasswordState,
            onValueChange = {
                viewModel.registerPasswordState = it
            },
            isError = viewModel.registerPasswordStateError,
            supportingText = {
                if(viewModel.registerPasswordStateError) {
                    Text(text = "this field must not be empty")
                }
            },
            label = { Text(text = stringResource(id = R.string.password)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(), visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = { viewModel.registerUser()},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {

            Text(text = stringResource(id = R.string.register))
        }
    }
}



fun showDatePicker(context: Context,viewModel: AuthViewModel) {

    val c: Calendar = Calendar.getInstance()
    val year: Int = c.get(Calendar.YEAR)
    val month: Int = c.get(Calendar.MONTH)
    val day: Int = c.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context, DatePickerDialog.OnDateSetListener { datePicker: DatePicker, i: Int, i1: Int, i2: Int ->
            viewModel.registerDateOfBirthState= String.format("%d-%d-%d",i2,i1,i)
            viewModel.registerDateOfBirthStateError= false
        },year, month, day)
    datePickerDialog.datePicker.maxDate = c.timeInMillis

    datePickerDialog.show();
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NYTArticlesDashboardTheme {

    }
}