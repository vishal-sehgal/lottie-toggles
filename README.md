# Lottie Toggles for Android 🎚⨕

An amazing android library which helps to implement your own custom-designed creative toggles inside the android apps.

[ ![Version](https://api.bintray.com/packages/codervishalsehgal/maven/Lottie-Toggles/images/download.svg) ](https://bintray.com/codervishalsehgal/maven/Lottie-Toggles/_latestVersion) ![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)

[![Say Thanks!](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg)](https://saythanks.io/to/vishalsehgal) ![Twitter URL](https://img.shields.io/twitter/url?label=Tweet%20LottieToggle&style=social&url=https%3A%2F%2Flottietoggles.vishalsehgal.dev) [![GitHub followers](https://img.shields.io/github/followers/CoderVishalSehgal.svg?style=social&label=Follow)](https://github.com/CoderVishalSehgal/lottie-toggles) ![Twitter Follow](https://img.shields.io/twitter/follow/vishalsehgal31?style=social)

<p align="center">
<img src="https://user-images.githubusercontent.com/20669217/100385522-dbe61680-3048-11eb-9f54-49794d3bc171.gif" width="60%" height="60%">
</p>

<p align="center">Download and try out the sample app in your mobile 📲</p>
<p align="center"><a href='https://play.google.com/store/apps/details?id=dev.vishalsehgal.lottietoggles.sample'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="60px"/></a></p>

### Show some 💙 and 🌟 the repo to support the project

[![GitHub stars](https://img.shields.io/github/stars/CoderVishalSehgal/lottie-toggles.svg?style=social&label=Star%20me)](https://github.com/CoderVishalSehgal/lottie-toggles)   [![GitHub forks](https://img.shields.io/github/forks/CoderVishalSehgal/lottie-toggles.svg?style=social&label=Fork%20me)](https://github.com/CoderVishalSehgal/lottie-toggles/fork)   [![GitHub watchers](https://img.shields.io/github/watchers/CoderVishalSehgal/lottie-toggles.svg?style=social&label=Watch)](https://github.com/CoderVishalSehgal/lottie-toggles)   \
[![GitHub followers](https://img.shields.io/github/followers/CoderVishalSehgal.svg?style=social&label=Follow)](https://github.com/CoderVishalSehgal/lottie-toggles)   [![Twitter Follow](https://img.shields.io/twitter/follow/vishalsehgal31?label=Follow&style=social)](https://twitter.com/vishalsehgal31)

[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=102)](https://opensource.org/licenses/MIT)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](https://github.com/CoderVishalSehgal/lottie-toggles/blob/master/LICENSE)

## Implementation 💻📲

**-> LottieSwitch**

<p align="start">
<img src="https://user-images.githubusercontent.com/20669217/100293977-7132cd80-2fab-11eb-94e2-5411da381305.gif" width="60%" height="60%">
</p>

Now we are going to add this type of toggle inside our android app which is basically called a switch but here it's getting lottiefied hence **LottieSwitch**.

**Step 1:**
Add this library inside `dependency{ }` block in your app's level `build.gradle` file and *Sync* your project.

<p align="start">
<img src="https://user-images.githubusercontent.com/20669217/100283107-da5a1700-2f92-11eb-9498-33b94d863859.png" width="30%" height="50%">
</p>

```groovy
dependency {

implementation 'dev.vishalsehgal.android:lottie-toggles:1.0.0'

}
```

**Step 2:**

Now, add your switch's Lottie `.json` file under `res/raw/day_nite_switch.json` directory.

<p align="start">
<img src="https://user-images.githubusercontent.com/20669217/100283756-0de97100-2f94-11eb-8a0e-8424154b421e.png" width="30%" height="50%">
</p>

**Step 3:**

In your layout file, Add the `LottieSwitch` view and set the switch file from previous step by adding `app:switch_lottieFile="@raw/day_night_switch"` attribute to the view.

```groovy
    <dev.vishalsehgal.lottietoggles.LottieSwitch
        android:id="@+id/my_switch"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:switch_lottieFile="@raw/day_night_toggle" />

```

**Step 4:**

Now, of course you may want to listen when the switch toggles! You can simply attach a listener by calling `setOnCheckedChangedListener{}` on it and add your logic inside it.

```groovy
findViewById<LottieSwitch>(R.id.lottie_switch).setOnCheckedChangedListener { toggleableView, isChecked ->

	    // Add your logic here when switch toggles.
	    // isChecked holds the value of current state of switch.
            Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
        }
```

**Step 5:**

Enjoy your brand new cool toggle.🥳


## Customization and Attributes 🎨

Available custom attributes for LottieToggles.
<table>
    <th>Attribute Name</th>
    <th>Example Value</th>
    <th>Description</th>
    <tr>
        <td>switch_lottieFile</td>
        <td>@raw/monster_toggle</td>
        <td>Sets the lottie file to the switch.</td>
    </tr>
    <tr>
        <td>switch_toggleSpeed</td>
        <td>2</td>
        <td>Sets the speed of switching toggles. Default is 3.</td>
    </tr>
    <tr>
            <td>switch_checked</td>
            <td>false/true</td>
            <td>Sets the default value for the switch.</td>
        </tr>
</table>

# How to Contribute 🛠
1. Fork the project.
2. Create your new feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Added this cool feature/ Fixed something.')
4. Push the branch to your remote repo. (git push origin my-new-feature)
5. From there Create new Pull Request to the Lottie Toggles.

# LICENSE 📜

```MIT License

Copyright (c) 2020 Vishal Sehgal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
