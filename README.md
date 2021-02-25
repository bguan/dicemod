# Dicemod

This is a educational project to accompany [Quick Tour of Java Booklet](https://bit.ly/javatourbook), which is used to to teach a bunch of teenagers who wants to hack minecraft but lacks the language skill.

The project is a simple Minecraft Mod adding a Dice as a new Block.

Based on Example 1 of [GreyGhost](https://github.com/TheGreyGhost)'s excellent [MineCraftByExample](https://github.com/TheGreyGhost/MinecraftByExample) project.t.

When you first placed it down, it will randomly choose a face as up.  

Like a crafting table you can put it down and take it back.

## Tips on Running it in Eclipse
* On command line, cd to a directory where you put all your projects e.g. ~/Projects, or C:\Projects
* (assuming you have git installed…), type and run: 
  * git clone https://github.com/bguan/dicemod/
* Still on command line, CD to dicemod folder, type and run 
  * gradlew genEclipseRuns --refresh-dependencies 
  * gradlew eclipse
* Open eclipse, import Dicemod project as gradle project into eclipse…. 
  * Right click on runClient.launch to run as Java application
  * Start and launch a Creative world, open inventory and search for Dice block
  * Put it down and… Behold!
* Open eclipse Help/Eclipse Marketplace… to install Web Developer Tools, BndTools, EcliPaint (you may need to reboot after each install) 
* Edit dice textures to change the look e.g. under src/main/resources/
  * assets/diceblockmod/textures/block/diceblock_face0.png


