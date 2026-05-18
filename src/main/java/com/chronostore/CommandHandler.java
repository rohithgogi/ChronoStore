package com.chronostore;

public class CommandHandler {
    private final Store store;

    public CommandHandler(Store store){
        this.store=store;
    }

    public String handle(String rawCommand){
        String[] parts=rawCommand.trim().split("\\s+",3);
        if(parts.length==0) return "ERROR: Empty command";

        return switch (parts[0].toUpperCase()){
            case "SET" ->{
                if(parts.length<3) yield "ERROR: SET requires key and value";
                store.set(parts[1],parts[2]);
                yield("OK");
            }
            case "GET" -> {
                if(parts.length<2) yield "ERROR: GET requires key";
                String val= store.get(parts[1]);
                yield val!=null?val:"NIL";
            }
            case "DELETE"-> {
                if(parts.length<2) yield "ERROR: DELETE requires key";
                yield store.delete(parts[1])?"OK":"NOT FOUND";
            }
            default -> "ERROR: Unknown command " + parts[0];
        };



    }

}
