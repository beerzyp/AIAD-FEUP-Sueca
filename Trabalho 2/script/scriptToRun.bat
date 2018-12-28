for /L %%i in (1 1 500000) do (
  java -jar final-aiad.jar 
  REM parentheses are more convenient for multiple commands within the loop
)