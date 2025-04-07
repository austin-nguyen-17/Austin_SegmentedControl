# Austin Segmented Control
:sheep: A segmented control (tabs) library for Jetpack Compose :sheep:



<img src="/images/demo.gif" alt="drawing" width="350"/>

## Examples
### A basic example
```
@Composable
fun Example() {
    var index by remember { mutableIntStateOf(0) }
    Column(
        Modifier.fillMaxSize().background(color = Color(0xFFFFFFFF))
    ) {
        Spacer(Modifier.height(64.dp))
        SegmentedControl(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            selectedIndex = index,
            onItemSelected = {
                index = it
            },
            items = listOf(
                SegmentedControlItem("Home"),
                SegmentedControlItem("Stats"),
                SegmentedControlItem("Transactions")
            )
        )
    }
}
```

### Other examples
You can find additional examples of usage and customization [here](app/src/main/java/me/austinng/austinsegmentedcontrol/MainActivity.kt).

## Installation

1. Add `Jitpack Repository` to your `settings.gradle` file:

```
maven { url 'https://jitpack.io' }
```

2. In your app’s `build.gradle` file, add the dependency:

```
dependencies {
	implementation 'com.github.austin17ng:Austin_SegmentedControl:0.0.10'
}
```
 
## Default attributes

```

object SegmentedButtonPropertiesDefault {
    @Composable
    fun colors() = SegmentedButtonProperties(
        containerBackgroundColor = Color(0x1F767680),
        containerPadding = 2.dp,
        containerCornerRadius = 9.dp,
        buttonElevation = 12.dp,
        buttonShadowColor = Color(0xFF000000),
        buttonBorderWidth = 0.5.dp,
        buttonBorderColor = Color(0x0A000000),
        buttonColor = Color(0xFFFFFFFF),
        buttonRadius = 7.dp,
        buttonVerticalPadding = 6.dp,
        buttonHorizontalPadding = 12.dp,
        animationDurationMillis = 500,
        labelFontSize = 12.sp,
        labelFontWeight = FontWeight.Medium,
        labelColor = Color(0xFF000000),
        easing = FastOutSlowInEasing,
    )

```

## Contributing

Feel free to contribute by submitting issues or pull requests. And please ensure that all pull requests are checked out from the `main` branch.

## License

Austin SegmentedControl is licensed under the MIT License.

