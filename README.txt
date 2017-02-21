###############################################################################################
# WARNING !!! Input .csv file should contain < 1000 - this is free Alchemy Language API trier.#
###############################################################################################

# Run program with default (./input.csv) input file path, default (./output.csv) output file path and default delimiter (;)
java -jar client.jar

# Run program with custom input file path and custom output file path
java -Dinput=input.csv -Doutput=output-data.csv -Ddelimiter=; -jar client.jar

# When client app prompt for credentials (credentials are fully configurable on the server side in Environment Variables)
username: clientApplication
password: p4ssw0rd