# CS370-Grocery-Inventory
TO COMPILE THIS PROJECT:

1. Open your command line interface (ex. Powershell, Terminal).
2. Navigate to the directory with the files using the cd command.
   (Ex. 
         >cd /Users/alexandratang/Desktop
   )
3. Enter "javac PhaseOne.java"
4. Enter "java PhaseOne [any pattern of UNIX flags and their arguments
   as seen below)".
   (Ex. 
         >java PhaseOne -d /Users/alexandratang/Desktop -i in.txt -o out.txt
   )
________________________________________

UNIX FLAGS:
-i (input.txt)
-o (output.txt) 
-l (log.txt) 
-d path
-g

These can be used in any order, like so:
> java 

Flags followed by items in parentheses indicate optional arguments. 
If the -i flag is not followed by a file, a JFileChooser will allow you to
select a file.

If the -o or -l flags are not followed by a file, a prompt will appear and
ask you for a filename and create one accordingly.

-d requires a path. It will use the path given to navigate a directory from
which the program will read or write from/to. 

   NOTE: Paths should be written like so:
      /Users/alexandratang/Desktop
   Do not include a "/" after your final location.


-g will open a GUI implementation of the program with a menu bar for a visual
representation of the program. To make modifications to products and save your
output and log files, please use the GUI implementation.

________________________________________

Input files should be written in the following format:

InventoryNumber_ItemDescription_Quantity_Price_DatePurchased_Timestamp

Ex: <br>
      123456_Bananas_6_6.99_January 6, 2017_Mon Oct 8 03:16:37 EDT 2018<br>
      234567_Grapes_492_7.32_October 15, 1997_Wed Oct 3 09:21:59 EDT 2018

________________________________________

Output files will be written in the following format:

InventoryNumber | ItemDescription | Quantity | Price | DatePurchased | Timestamp

Ex:<br>
      123456 | Bananas | 6 | 6.99 | January 6, 2017 | Mon Oct 8 03:16:37 EDT 2018<br>
      234567 | Grapes | 492 | 7.32 | October 15, 1997 | Wed Oct 3 09:21:59 EDT 2018

________________________________________

Log files will be written like the example below:

Ex:<br>
      Item added: Product #583943 (Chicken) on Wed Oct 10 03:16:37 EDT 2018


