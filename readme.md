# Inventory Tracker
**By Noah Seligson**

##Initialization
    Once the application opens you will have the option to either either start or exit the program.
    Start: Open the inventory tracker.
    Exit: Collapse the application.

##Main screen
    You will be shown the main screen of the application.
    The table contains all currently added items. (Max: 100)

##Inventory Operations
###Add Item
    The add item button will open a new tab where you can type in a name, serial number and value.
    Once all requirements are met, click the complete button to add the item to the item table.
    
    Item Requirements:
        Name: Must be at least 2 characters and no more than 256 characters. (Correct Example: Balloon)
        Serial Number: Must be exactly 10 characters and contain no special characters. (Correct Example: 12345ABCDE)
        Value: Must be a value in U.S. dollars. (Correct Example: $5.00 or 5.00)
###Search Bar
    The user can search by either name or serial number.
    Type in the search bar and click search to see which items match your search preferences.
    Click exit search to return to show all items.

###Delete All Items
    Removes all inventory items.

###Sort
    Clicking a column header will sort the inventory items by that header property.

###Selecting an Item
    Selecting an item gives the user the option to either edit or delete said item
####Edit item
    The same operations of the add item button apply.
    Click the complete button to save changes to the selected item
####Delete item
    Removes the selected item.

##Menu Bar
    The Menu Bar gives you two menu options: File and Exit:
    File: Load and Save (See Below)
    Exit: Gives you an exit button that will collapse the application.
###Load
    The user will load an already created list by selecting a file.
    The user must specificy at the bottom what type of file they want. (HTML, TSV, JSON)

    Note: If the user has an inventory in the table when this button is clicked, they will be inquired to save the current inventory before loading.
###Save
    The user will save the list to any of the available file format chosen at the bottom. (HTML, TSV, JSON)
    
    Note: At least one item must be in the current inventory before the option to save a list is available.
        






