import com.alibaba.fastjson.JSON;

/**
 * @ClassName JsonDcoder
 * @Description 序列化实现
 * @Author peco
 * @Date 2022/9/26 16:17
 */
public class JSONEncoder implements Encoder {
    @Override
    public byte[] ecode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
