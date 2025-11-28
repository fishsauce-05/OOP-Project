cd src
javac -d ../out com/burgers/*.java com/burgers/item/*.java com/burgers/order/*.java com/burgers/ui/*.java

cd ../out
start javaw com.burgers.ui.MenuGUI

exit