package crm.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crm.util.ImageUtil;

/**
 *生成验证码的servet
 * @author 15056
 *
 */
@WebServlet("/BufferimageServlet")
public class BufferimageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> bi = ImageUtil.createImage();
		//生成图片
		BufferedImage image = (BufferedImage) bi.get("image");
		//生成验证码
		String checkcode = (String)bi.get("code");
		HttpSession session = requset.getSession();
		//把验证码保存到session中
		session.setAttribute("checkcode", checkcode);
		ImageIO.write(image, "jpg", response.getOutputStream());
		this.doPost(requset, response);
	}

	@Override
	protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
	}

	
	

}