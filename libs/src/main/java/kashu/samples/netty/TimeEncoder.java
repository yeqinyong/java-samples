package kashu.samples.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author 叶勤勇(卡叔)
 * @date 2020/03/29
 */
public class TimeEncoder extends MessageToByteEncoder<Integer> {
	@Override
	protected void encode(ChannelHandlerContext ctx, Integer msg, ByteBuf out) {
		out.writeInt(msg.intValue());
	}
}
