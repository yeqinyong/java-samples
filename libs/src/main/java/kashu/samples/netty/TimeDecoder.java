package kashu.samples.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author 叶勤勇(卡叔)
 * @date 2020/03/29
 */
public class TimeDecoder extends ByteToMessageDecoder { // (1)
	@Override
	// ByteToMessageDecoder calls the decode() method with an internally
	// maintained cumulative buffer whenever new data is received.
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) { // (2)
		if (in.readableBytes() < 4) {
			// decode() can decide to add nothing to out where there is not enough data in the
			// cumulative buffer. ByteToMessageDecoder will call decode() again when there is more
			// data received.
			return; // (3)
		}

		// If decode() adds an object to out, it means the decoder decoded a message successfully.
		// ByteToMessageDecoder will discard the read part of the cumulative buffer. Please remember
		// that you don't need to decode multiple messages. ByteToMessageDecoder will keep calling
		// the decode() method until it adds nothing to out.
		out.add(in.readBytes(4)); // (4)
	}
}
