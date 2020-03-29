package kashu.samples.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author 叶勤勇(卡叔)
 * @date 2020/03/29
 */
public class TimeDoceder2 extends ReplayingDecoder<Void> {
	@Override
	protected void decode(
			ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
		out.add(in.readBytes(4));
	}
}
