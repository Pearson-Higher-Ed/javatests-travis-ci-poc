#!/usr/bin/env bash

echo "component: $component"
echo "feature_branch: $feature_branch"
js-beautify --version

install_elements_sdk() {
echo -e "******************************\\n    Installing elements-sdk: $1   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/elements-sdk.git
cd elements-sdk
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/elements-sdk/build/dist.compounds.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/functional/jsfiles/
instrument_file_elements-sdk dist.compounds
cp $UX_TEST_PLATFORM_ROOT_DIR/elements-sdk/build/eventInstantiator.compounds.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/functional/jsfiles/
instrument_file_elements-sdk eventInstantiator.compounds
cp $UX_TEST_PLATFORM_ROOT_DIR/elements-sdk/build/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/css/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/elements-sdk/build/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/elements-sdk/build/icons $UX_TEST_PLATFORM_ROOT_DIR/
}

install_compounds_sdk() {
echo -e "******************************\\n    Installing compounds sdk: $1   \\n******************************"
cd $UX_TEST_PLATFORM_ROOT_DIR/
git clone https://github.com/Pearson-Higher-Ed/compounds.git &>/dev/null
cd compounds
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/compounds/build/dist.compounds.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/functional/jsfiles/
instrument_file compounds dist.compounds
cp $UX_TEST_PLATFORM_ROOT_DIR/compounds/build/eventInstantiator.compounds.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/functional/jsfiles/
instrument_file compounds eventInstantiator.compounds
cp $UX_TEST_PLATFORM_ROOT_DIR/compounds/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/css/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/compounds/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/compounds/node_modules/pearson-elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
}

install_elements() {
echo -e "******************************\\n    Installing elements: $1   \\n******************************"
cd $UX_TEST_PLATFORM_ROOT_DIR/
git clone https://github.com/Pearson-Higher-Ed/elements.git &>/dev/null
cd elements
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/elementsSDK/css/
ls -ltr $UX_TEST_PLATFORM_ROOT_DIR/elements/dist/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
}

install_appHeader(){
echo -e "******************************\\n    Installing app-header: $1  \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/app-header.git
cd app-header
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/app-header/build/dist.app-header.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/appHeader/
instrument_file appHeader dist.app-header
cp -R $UX_TEST_PLATFORM_ROOT_DIR/app-header/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/app-header/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/appHeader/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/app-header/images $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/
}

install_contextualHelp(){
echo -e "******************************\\n    Installing contextual-help: $1   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/contextual-help.git
cd contextual-help
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/contextual-help/build/dist.contextual-help.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/contextualHelp/
instrument_file contextualHelp dist.contextual-help
cp -R $UX_TEST_PLATFORM_ROOT_DIR/contextual-help/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/contextual-help/node_modules/pearson-elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/contextual-help/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/contextualHelp/
}

install_drawer(){
echo -e "******************************\\n    Installing drawer: $1   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/drawer.git
cd drawer
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/drawer/build/dist.drawer.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/drawer/
instrument_file drawer dist.drawer
}

install_componentArchetype(){
echo -e "******************************\\n    Installing component-archetype: $1  \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/component-archetype.git
cd component-archetype
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/component-archetype/build/dist.component-name.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/componentArchetype/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/component-archetype/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/component-archetype/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/componentArchetype/
}

install_avatarDisplay(){
echo -e "******************************\\n    Installing avatar-display: $1    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/avatar-display.git
cd avatar-display
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/avatar-display/build/dist.avatar-display.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/avatarDisplay/
instrument_file avatarDisplay dist.avatar-display
cp -R $UX_TEST_PLATFORM_ROOT_DIR/avatar-display/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/avatar-display/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/avatarDisplay/
}

install_slider(){
echo -e "******************************\\n    Installing slider: $1    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/slider.git
cd slider
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/slider/build/dist.slider.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/slider/
instrument_file slider dist.slider
cp $UX_TEST_PLATFORM_ROOT_DIR/slider/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/slider/
cp $UX_TEST_PLATFORM_ROOT_DIR/slider/slider.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/slider/
}

install_alerts(){
echo -e "******************************\\n    Installing alerts: $1   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/alerts.git
cd alerts
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/alerts/build/dist.alerts.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/alerts/
instrument_file alerts dist.alerts
cp -R $UX_TEST_PLATFORM_ROOT_DIR/alerts/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/alerts/node_modules/pearson-elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/alerts/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/alerts/
}

install_pagination(){
echo -e "******************************\\n    Installing pagination: $1    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/pagination.git
cd pagination
git checkout $1
npm install &>/dev/null
#npm run copy-utils
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/pagination/build/dist.pagination.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/pagination/
instrument_file pagination dist.pagination
cp $UX_TEST_PLATFORM_ROOT_DIR/pagination/build/eventInterface.pagination.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/pagination/
instrument_file pagination eventInterface.pagination
cp -R $UX_TEST_PLATFORM_ROOT_DIR/pagination/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/pagination/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/pagination/
}

install_modal(){
echo -e "******************************\\n    Installing modal: $1   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/modal.git
cd modal
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/modal/build/dist.modal.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/modal/
instrument_file modal dist.modal
cp $UX_TEST_PLATFORM_ROOT_DIR/modal/build/dev.modal.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/modal/
instrument_file modal dev.modal
cp -R $UX_TEST_PLATFORM_ROOT_DIR/modal/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/modal/node_modules/pearson-elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/modal/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/modal/
}

install_loadingIndicator(){
echo -e "******************************\\n    Installing loading Indicator: $1    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/loading-indicator.git
cd loading-indicator
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/loading-indicator/build/dist.loadingIndicator.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/loadingIndicator/
instrument_file loadingIndicator dist.loadingIndicator
cp $UX_TEST_PLATFORM_ROOT_DIR/loading-indicator/build/dev.loadingIndicator.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/loadingIndicator/
instrument_file loadingIndicator dev.loadingIndicator
cp -R $UX_TEST_PLATFORM_ROOT_DIR/loading-indicator/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/loading-indicator/node_modules/pearson-elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/loading-indicator/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/loadingIndicator/
}

install_coachMark(){
echo -e "******************************\\n    Installing coach-mark: $1    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/coach-mark.git
cd coach-mark
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp $UX_TEST_PLATFORM_ROOT_DIR/coach-mark/build/dist.coach-mark.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/coachMark/
instrument_file coachMark dist.coach-mark
cp $UX_TEST_PLATFORM_ROOT_DIR/coach-mark/build/dev.coach-mark.js $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/jsfiles/coachMark/
instrument_file coachMark dev.coach-mark
cp -R $UX_TEST_PLATFORM_ROOT_DIR/coach-mark/node_modules/pearson-elements/dist/fonts $UX_TEST_PLATFORM_ROOT_DIR/
cp -R $UX_TEST_PLATFORM_ROOT_DIR/coach-mark/node_modules/pearson-elements/dist/icons $UX_TEST_PLATFORM_ROOT_DIR/
cp $UX_TEST_PLATFORM_ROOT_DIR/coach-mark/node_modules/pearson-elements/dist/css/elements.css $UX_TEST_PLATFORM_ROOT_DIR/src/main/java/standAlone/css/coachMark/
}

instrument_file(){
sed -i -e 's/\/\/# sourceMappingURL/sourceMappingURL/g' ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2.js
mv ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2.test.js
js-beautify ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2.test.js >> ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2.js
nyc instrument ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2.js >> ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/standAlone/jsfiles/$1/$2-instrumented.js
}

instrument_file_elements-sdk(){
sed -i -e 's/\/\/# sourceMappingURL/sourceMappingURL/g' ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1.js
mv ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1.test.js
js-beautify ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1.test.js >> ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1.js
nyc instrument ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1.js >> ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/$1-instrumented.js
}

# Below conditions are to install the components specific to its feature branch.
if [[ $component == "elements-sdk" ]]
then
install_elements_sdk $feature_branch

elif [[ $component == "compounds" ]]
then
install_compounds_sdk $feature_branch

elif [[ $component == "elements" ]]
then
install_elements $feature_branch

elif [[ $component == "app-header" ]]
then
install_appHeader $feature_branch

elif [[ $component == "contextual-help" ]]
then
install_appHeader master
cd ..
install_contextualHelp $feature_branch

elif [[ $component == "drawer" ]]
then
install_drawer $feature_branch

elif [[ $component == "component-archetype" ]]
then
install_componentArchetype $feature_branch

elif [[ $component == "avatar-display" ]]
then
install_avatarDisplay $feature_branch

elif [[ $component == "slider" ]]
then
install_slider $feature_branch

elif [[ $component == "alerts" ]]
then
install_alerts $feature_branch

elif [[ $component == "pagination" ]]
then
install_pagination $feature_branch

elif [[ $component == "modal" ]]
then
install_modal $feature_branch

elif [[ $component == "loading-indicator" ]]
then
install_loadingIndicator $feature_branch

elif [[ $component == "coach-mark" ]]
then
install_coachMark $feature_branch

# Below condition is to install all the "master" branch of components for the regression test run, regression split into 3 suites
elif [[ $component == "regression" ]]
then
install_appHeader master &
install_alerts master &
install_drawer master &
install_contextualHelp master &
install_avatarDisplay master &
install_slider master &
install_pagination master &
install_modal master &
install_loadingIndicator master &
install_coachMark master &
install_elements_sdk master &
fi
if [[ $TEST_SUITE =~ "elements_styles_sdk" ]]
then
install_elements_sdk master &
fi
if [[ $TEST_SUITE =~ "elements_functional_sdk" ]]
then
install_elements_sdk master &
fi
jobs -l
wait
jobs -l