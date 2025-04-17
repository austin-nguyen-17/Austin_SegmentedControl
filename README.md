# Austin Segmented Control
:sheep: A segmented control (tabs) library for Jetpack Compose :sheep:



<img src="/images/demo_v200.gif" alt="drawing" width="350"/>

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
            selectedIndex = index,
		    itemWidthMode = ItemWidthMode.Proportional,
            segmentedControlProperties = SegmentedControlPropertiesDefault.values().copy(offset = 16.dp, indicatorHorizontalPadding = 6.dp),
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
	implementation 'com.github.austin17ng:Austin_SegmentedControl:2.0.0'
}
```
 
## Default properties

```

object SegmentedControlPropertiesDefault {
    @Composable
    fun values() = SegmentedControlProperties(
        offset = 0.dp,
        containerBackgroundColor = Color(0x1F767680),
        containerPadding = 2.dp,
        containerCornerRadius = 9.dp,
        indicatorElevation = 12.dp,
        indicatorShadowColor = Color(0xFF000000),
        indicatorBorderWidth = 0.5.dp,
        indicatorBorderColor = Color(0x0A000000),
        indicatorColor = Color(0xFFFFFFFF),
        indicatorRadius = 7.dp,
        indicatorVerticalPadding = 6.dp,
        indicatorHorizontalPadding = 12.dp,
        animationDurationMillis = 500,
        labelFontSize = 12.sp,
        labelFontWeight = FontWeight.Medium,
        labelColor = Color(0xFF000000),
        easing = FastOutSlowInEasing,
    )
}

```

## Contributing

Feel free to contribute by submitting issues or pull requests. And please ensure that all pull requests are checked out from the `main` branch.

## Related Projects
- [Austin Tab](https://github.com/austin17ng/austin-tab) –🐑 An adaptive and lightweight tab layout for Android 🐑.


## License

Austin SegmentedControl is licensed under the MIT License.

