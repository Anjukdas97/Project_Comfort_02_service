package ServicePackage;


// Declare any non-default types here with import statements

interface ComfortInterface {
     boolean AcPressed(boolean acvalue);
     boolean MaxPressed(boolean maxvalue);
     java.util.List<String> getmaxList();
     java.util.List<String>getdefrost();
     java.util.List<String>getautoList();
     boolean PowerPressed(boolean powervalue);
     boolean AutoValue(boolean autovalue);
     boolean DefrostValue(boolean value);
     boolean RearValue(boolean value);
     void TempValue(int value);
     void SpeedValue(int value);

}