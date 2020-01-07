**Please consider donating if you find my work usefull.**

[![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://www.paypal.me/schellenberga)

## About
### No injects, no interaction with wow.exe

NO AUTH KEY NEEDED ANYMORE, everything is free and everything related to auth keys has been removed.

The bot does not interact with the World of Warcraft process in anyway. It should be invisible for the World of Warcraft Warden anticheat system. I do not have a 100% guarantee. Your ingame character on the other hand can be seen and can be reported by other players if you sit in one place for whole days. So use your brain.

It renames itself on every start. It reads only the pixel data on the screen and that is all it needs to work. The mouse and key input is simulated.

There are many options available to adjust. You dont have to, but you can. The bot comes preconfigured.

## Note
The bot is seperated into 2 repositories.

https://github.com/oppahansi/fishylauncher
https://github.com/oppahansi/fishy

To run the bot download the latest release in this repository, I zipped all you needed. Or build it yourself.
Basically you need fishy.jar from the fishy repository and the fishylauncher.jar from this repository + the lib folder from the fishy repository to run the bot.

## Requirements
* Java 8, the bot wont work with later versions, due to changes to JavaFX

## How To:
Sample Image
![Sample Image](https://i.imgur.com/kvL9BrV.jpg)
* Start the Fishy Bot.
* Switch to World of Warcraft.
* Start fishing by pressing the fishing key.
* Switch back to Fishy Bot and take a sample image exactly when a fish bites by pressing the sample hotkey.


Colors Settings
![Colors Settings](https://i.imgur.com/9WUkUN8.jpg)
* Use the bobber color and biting color hotkeys to set the colors.
* Hover with the mouse over the sample image and pick the color respectively. I recommend using the feathers on the bobber for bobber color and the water splash for the biting color.
* Try to reach a good contrast between the bobber/biting color and between the background/surrounding colors.
* If bobber/biting is not found, try to increase the seinsitivity respectively. The higher the sensitivity the bigger the the color is allowed to differ from the picked color.

Fishing Plan
![Fishing Plan](https://i.imgur.com/ThlQcng.jpg)
* Fishing cycles defines how long to wait for a bite. 28000ms mean 28 seconds.
* Search dealy is used to wait right after a fishing cycle has started. It is used to wait until the fishing bobber is completely visible.
* Check shift loot if you dont have autoloot enabled.
* Check use buff if you want to fish using a buff.
* Buff duration defines how long to wait between rebuffing.
* Buff count defines the buff amount in your bag.

Exit Plan
![Exit Plan](https://i.imgur.com/2VvJZBk.jpg)
* Time defines the exact time when you want to stop. The time format has to be followed exactly like is shown.
* Hooks defines after how many fish bites you want to exit.
* Casts defines after how many fishing casts/fish cycle starts you want to exit.
* Hours defines after how many hours of fishing you want to stop.
* Buffs used up means you will stop after the buffs are used up. Usefull for areas where your skill is too low without a buff to fish.
* Never means you wont stop fishing. Ever.
* Check shut down pc if you want to shut down your pc after a fishing session.
* Check logout if your character should be logged out after a fishing sessions.
* Adjustable Fishing Area

Fishing/Search Area
![Fishing Area](https://i.imgur.com/ZUmFz6j.jpg)
* Resize the transparent rectangle to cover the area where the fishing bobber would land. Try to only cover the water like in the screenshot.
* Double left click the rectangle to start the fishing session.
* Left click one more time to set World of Warcraft to the active window.
* Fishing sessions starts a bit delayed. Around 10 seconds so you have enough time to get World of Warcraft to front.

Thats it. HF & GL

Important graphics settings
![Graphics settings](https://i.imgur.com/pA3DehC.jpg)
* Display Mode has to be Windowed or Windowed Borderless. Fullscreen wont work.
* If you do not play World of Warcraft Classic make sure you don't use "Glowing Quest Objects" in your Interface Options
* You can have any graphics quality you want. The marked settings in the picture should have atleast the level in the picture.
* Liquid Detail: Fair
* Particle Density: High

Graphics settings camparison
![Settings comparison](https://i.imgur.com/OszE4L4.jpg)
* As you can see with Liquid Detail on Fair and Particle Density on High you reach the best contrast between the bobber/biting and the water/surrounding.
