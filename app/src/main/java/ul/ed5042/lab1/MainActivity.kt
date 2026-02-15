package ul.ed5042.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ul.ed5042.lab1.ui.theme.Lab1Theme
import ul.ed5042.lab1.ui.theme.Pink40
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QcaCalculationLayout()
        }
    }
}

@Composable
fun QcaCalculationLayout(modifier: Modifier = Modifier) {
    //adding stets for 5modules, 5grades, 5weight(the way i have no idea what weight is 😀)
    var module1 by remember { mutableStateOf("") }
    var grade1 by remember { mutableStateOf("") }
    var weight1 by remember { mutableStateOf("") }

    var module2 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var weight2 by remember { mutableStateOf("") }

    var module3 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }
    var weight3 by remember { mutableStateOf("") }

    var module4 by remember { mutableStateOf("") }
    var grade4 by remember { mutableStateOf("") }
    var weight4 by remember { mutableStateOf("") }

    var module5 by remember { mutableStateOf("") }
    var grade5 by remember { mutableStateOf("") }
    var weight5 by remember { mutableStateOf("") }


    //call calcQca, convert the weights to ints, use elvis like the man told us to
    val result = calculateQca(
        grade1, weight1.toIntOrNull() ?: 0,
        grade2, weight2.toIntOrNull() ?: 0,
        grade3, weight3.toIntOrNull() ?: 0,
        grade4, weight4.toIntOrNull() ?: 0,
        grade5, weight5.toIntOrNull() ?: 0
    )

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.qca_calculator),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(R.string.enter_data),
            style = MaterialTheme.typography.bodyLarge
        )

        ModuleRow(module1, "Module 1", grade1, weight1,
        onModuleChange = { newModule ->
            module1 = newModule
        },
        onGradeChange = { newGrade ->
            grade1 = newGrade
        },
        onWeightChange = { newWeight ->
            weight1 = newWeight
        }
        )

        ModuleRow(module2, "Module 2", grade2, weight2,
            onModuleChange = { newModule ->
                module2 = newModule
            },
            onGradeChange = { newGrade ->
                grade2 = newGrade
            },
            onWeightChange = { newWeight ->
                weight2 = newWeight
            }
        )

        ModuleRow(module3, "Module 3", grade3, weight3,
            onModuleChange = { newModule ->
                module3 = newModule
            },
            onGradeChange = { newGrade ->
                grade3 = newGrade
            },
            onWeightChange = { newWeight ->
                weight3 = newWeight
            }
        )

        ModuleRow(module4, "Module 4", grade4, weight4,
            onModuleChange = { newModule ->
                module4 = newModule
            },
            onGradeChange = { newGrade ->
                grade4 = newGrade
            },
            onWeightChange = { newWeight ->
                weight4 = newWeight
            }
        )

        ModuleRow(module5, "Module 5", grade5, weight5,
            onModuleChange = { newModule ->
                module5 = newModule
            },
            onGradeChange = { newGrade ->
                grade5 = newGrade
            },
            onWeightChange = { newWeight ->
                weight5 = newWeight
            }
        )

        Text(
            text = "Your QCA:",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = modifier.testTag("Result"),
            text = result,
            style = MaterialTheme.typography.displayMedium
        )

    }
}

fun calculateQca(
    grade1: String, weight1: Int,
    grade2: String, weight2: Int,
    grade3: String, weight3: Int,
    grade4: String, weight4: Int,
    grade5: String, weight5: Int
): String {
    //map grades to qca values? idfk oh wait itsx qpv
    val gradeMap = mapOf(
        "F" to 0.0,
        "D2" to 1.2,
        "D1" to 1.6,
        "C3" to 2.0,
        "C2" to 2.4,
        "C1" to 2.6,
        "B3" to 2.8,
        "B2" to 3.0,
        "B1" to 3.2,
        "A2" to 3.6,
        "A1" to 4.0
    )
    //return empty string to prevent warnings.
    return ""
}


@Composable
fun ModuleRow(
    //from what i can see this one js displays data, so dumb ui component
    moduleInput: String,
    moduleLabel: String,
    gradeInput: String,
    weightInput: String,
    onModuleChange: (String) -> Unit,
    onGradeChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp, top = 5.dp)
            .testTag(moduleLabel),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = moduleInput,
            label = {Text(moduleLabel)},
            onValueChange = onModuleChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(5.dp))
        TextField(
            value = gradeInput,
            label = { Text(stringResource(R.string.module_grade)) },
            onValueChange = onGradeChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(5.dp))
        TextField(
            value = weightInput,
            label = { Text(stringResource(R.string.module_weight)) },
            onValueChange = onWeightChange,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QcaCalculationPreview() {
    Lab1Theme {
        QcaCalculationLayout()
    }
}