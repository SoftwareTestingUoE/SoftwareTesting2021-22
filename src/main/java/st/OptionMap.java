package st;

import java.util.HashMap;

public class OptionMap {
	
    private HashMap<String, Option> options;
    private HashMap<String, Option> shortcuts;
    
    public OptionMap() {
    	options = new HashMap<String, Option>();
    	shortcuts = new HashMap<String, Option>();
    }
    
    public void store(Option option, String shortcut) {
    	String name = option.getName();
    	Type type = option.getType();
    	
    	if (type == Type.NOTYPE || !isOptionValid(option, shortcut)) {
    		throw new IllegalArgumentException("Illegal argument provided in store(....) method.");
    	}
    	
    	if (optionExists(name)) {
    		Option oldOption = getOption(name);
    		oldOption.setType(type);
    		
    		options.put(name, oldOption);
    		if (!shortcut.equals("")) {
        		shortcuts.put(shortcut, oldOption);
        	}
    	} else {
        	options.put(name, option);
        	if (!shortcut.equals("")) {
        		shortcuts.put(shortcut, option);
        	}
    	}
    }
    
    public Option getOption(String key) {
    	
    	if (options.containsKey(key)) {
    		return options.get(key);
    	} else {
    		throw new RuntimeException("Option not defined for key: " + key + ".");
    	}
    }
    
    public Option getShortcut(String key) {
    	
    	if (shortcuts.containsKey(key)) {
    		return shortcuts.get(key);
    	} else {
    		throw new RuntimeException("Option shortuct not defined for key: " + key + ".");
    	}
    }
    
    public Option getOptionByNameOrShortcut(String key) {
    	key = key.trim(); 	
    	if(key.startsWith("--")) {
    		return getOption(key.substring(2, key.length()));
    	} else if (key.startsWith("-")) {
    		return getShortcut(key.substring(1, key.length()));
    	} else if (options.containsKey(key)) {
    		return options.get(key);
    	} else if (shortcuts.containsKey(key)) {
    		return shortcuts.get(key);
    	} else {
    		throw new RuntimeException("Option not defined for key: " + key + ".");
    	}
    }
    
    public String getValue(String key) {
    	return getOptionByNameOrShortcut(key).getValue();
    }
    
    public Type getType(String key) {
    	return getOptionByNameOrShortcut(key).getType();
    }
    
    public boolean optionExists(String key) {
    	return options.containsKey(key);
    }
    
    public boolean shortcutExists(String key) {
    	return shortcuts.containsKey(key);
    }
    
    public boolean optionOrShortcutExists(String key) {
    	return optionExists(key) || shortcutExists(key);
    }
    
    public void setShortcut(String optionName, String shortcutName) {
    	Option option = options.get(optionName);
    	if (option != null) {
    		shortcuts.put(shortcutName, option);
    	}
    }
    
    public void setValueWithOptionName(String name, String value) {
    	Option option = options.get(name);
    	if (option != null) {
    		option.setValue(value);
    	}
    	
    }
    
    public void setValueWithOptionShortcut(String shortcut, String value) {
    	Option option = shortcuts.get(shortcut);
    	if (option != null) {
    		option.setValue(value);
    	}
    }
    
    private boolean isOptionValid(Option o, String shortcut) {
    	if (o.getName() == null) {
    		return false;
    	}
    	if (o.getName().isEmpty()) {
    		return false;
    	}
    	if (!o.getName().matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
    		return false;
    	}
    	if (shortcut == null || !shortcut.isEmpty() && !shortcut.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
    	    return false;
    	}
    	
    	return true;
    }

	@Override
	public String toString() {
		return "Options Map: \n" + options + "\nShortcuts Map:\n" + shortcuts;
	}

}
