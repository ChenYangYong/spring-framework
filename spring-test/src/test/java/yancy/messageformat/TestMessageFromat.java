package yancy.messageformat;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;

public class TestMessageFromat {
	public static void main(String[] args) {
		Object[] objects = {"贵阳", new Date(), "晴朗"};
		//只指定应用对象：objects
		MessageFormat mf = new MessageFormat("当前时间：{1}，地点：{0}，天气：{2}");//索引对应于objects元素索引
		String result = mf.format(objects);
		System.out.println(result);

		Object[] objects1 = {new Date(), "美国", "晴朗"};//指定date或time，传入Date 实例
		MessageFormat mf1 = new MessageFormat("当前时间：{0,time}，地点：{1}，天气：{2}", Locale.US);
		String result1 = mf1.format(objects1);
		System.out.println(result1);
	}
}
