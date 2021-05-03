# CurrencyConverterDemo

Material layout, Constraint layout , appcompact, navigation fragment libraries
Added for Designing layout and fragment transformation

testImplementation and androidTestImplementation libraries for unit testing and mock testing the code

kotlin libraries and plugins for kotlin usage in project

okhttp and retrofit for networking

gson library for JSON parsing

timber for log

lifecycle components libraries for MVVM architecture

Mock unit testing code is present in test package
Filename - KoinCheck is mock testing koin implementation

Junit testing code is present in androidtest package
Filename - CurrencyConverterFragmentTest is unit testing the calculaion functionality of currency conversion

CurrencyConverterFragment does call the API and process the data using json parsing and storing in 
arraylist of Rates type . removing objects from this list where buyTT value is N/A or On App

project is implemented with koin injection and in MVVM pattern

resources file has primary and secorndary colors by which one can easily transform the UI theme
also dimen file has few project level dimensions used for Consistency in UI



