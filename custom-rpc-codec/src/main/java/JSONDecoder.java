import com.alibaba.fastjson.JSON;

/**
 * @ClassName JsonDcoder
 * @Description 反序列化实现
 * @Author peco
 * @Date 2022/9/26 16:17
 */
public class JSONDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
