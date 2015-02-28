package de.jeha.fwj.gson;

import com.google.gson.Gson;

public class GsonUnserialize {

    public static void main(String[] args) {
        final String json = "{\"dateCreated\":1.340261112e+12,\"dateModified\":1.34026111557e+12,\"vectorClock\":{\"opossum\":1.34026111557e+12}}";
        final Gson gson = new Gson();

        TestObject o = gson.fromJson(json, TestObject.class);

        System.out.println(o.dateCreated);
        System.out.println(o.dateModified);
    }

    static class TestObject {
        public Long dateCreated;
        public Long dateModified;
    }

}
