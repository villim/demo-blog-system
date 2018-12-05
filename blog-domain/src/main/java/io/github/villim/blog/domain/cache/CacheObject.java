package io.github.villim.blog.domain.cache;

public class CacheObject {

    private final String className;

    private final Object object;

    public CacheObject(String className, Object object) {
        super();
        this.className = className;
        this.object = object;
    }

    public String getClassName() {
        return className;
    }

    public Object getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "CacheObject [className=" + className + ", jsonObject=" + object + "]";
    }
}
