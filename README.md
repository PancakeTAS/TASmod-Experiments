# TASmod-Experiments
An experimental rewrite of [TASmod](https://github.com/ScribbleLP/TASMod)

Lets you record and play back tickperfect inputs.

This mod is currently only for Forge 1.12.2 and will update/downgrade once 1.12.2 works properly

## Credits
...

## Development
### Setup
In order to help you will need ForgeNoGradle and Eclipse. Please note that before updating you also need to close the project!
 - Download ForgeNoGradle from here: https://mgnet.work/ForgeNoGradle-1.0.2.jar
 - Move ForgeNoGradle into the TASmod-Experiments/ directory
 - Run ForgeNoGradle and enjoy ~20 seconds of pong
### Launch game in dev environment
- Run either the `TASmod-Experiments.launch` to start the client or `TASmod-Experiments-server.launch` to start the server.
### Building
- At the top bar, go to `Project->Clean`. If you don't have `Project->Build Automatically` enabled, you need to run `Project->Build All`
- Run `TASmod-Experiments-export.launch`
- You can find the jar in `TASmod-Experiments/build/`, where `export-reobf-mixin.jar` is the correct jar.