Running Instructions :

-- Reads Input from file
java -jar target/parking-lot-1.0.jar <filename>
    -- filename is the path of input file

OR

-- Reads Input from stdin
java -jar target/parking-lot-1.0.jar

These commands are supported:

create_parking_lot <capacity>
    -- capacity [integer]

park <regno> <color>
    -- regno [string without spaces]
    -- color [string without spaces]

unpark <slotno>
    -- slotno [integer]

status


registration_numbers_for_cars_with_colour <color>
    -- color [string without spaces, null if none provided]

slot_numbers_for_cars_with_colour <color>
    -- color [string without spaces, null if none provided]

slot_number_for_registration_number <regno>
    -- regno [string without spaces, null if none provided]

exit
    -- exits the program


Sample input :
-- with input file
java -jar target/parking-lot-1.0.jar test-input-file.txt


-- with stdin
java -jar target/parking-lot-1.0.jar
create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
park KA-01-P-333 White
park DL-12-AA-9999 White
status
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KA-01-HH-3141
slot_number_for_registration_number MH-04-AY-1111
exit


