package com.example.juegosgratis.model.game;

public class RequisitosMinimos {

    public RequisitosMinimos() {
    }

    public RequisitosMinimos(String os, String processor, String memory, String graphics, String storage) {
        this.os = os;
        this.processor = processor;
        this.memory = memory;
        this.graphics = graphics;
        this.storage = storage;
    }

    public String getOs() {
        return os;
    }

    public String getProcessor() {
        return processor;
    }

    public String getMemory() {
        return memory;
    }

    public String getGraphics() {
        return graphics;
    }

    public String getStorage() {
        return storage;
    }

    private String os;
    private String processor;
    private String memory;
    private String graphics;
    private String storage;
}
