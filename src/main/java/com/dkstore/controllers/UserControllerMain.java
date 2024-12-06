package com.dkstore.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dkstore.models.ChiTietGioHang;
import com.dkstore.models.ChiTietHoaDon;
import com.dkstore.models.GioHang;
import com.dkstore.models.HoaDon;
import com.dkstore.models.PhuongThucThanhToan;
import com.dkstore.models.Product;
import com.dkstore.models.ThanhToan;
import com.dkstore.models.User;
import com.dkstore.repository.ChiTietGioHangRepository;
import com.dkstore.repository.GioHangRepository;
import com.dkstore.repository.HoaDonRepository;
import com.dkstore.repository.ThanhToanRepository;
import com.dkstore.services.ChiTietGioHangService;
import com.dkstore.services.GioHangService;
import com.dkstore.services.ProductService;
import com.dkstore.services.SanPhamTonKhoService;
import com.dkstore.services.ThanhToanService;
import com.dkstore.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserControllerMain {
	@Autowired
	private UserService userService;
	@Autowired
	private GioHangService gioHangService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SanPhamTonKhoService sanPhamTonKhoService;
	@Autowired
	private ChiTietGioHangRepository chiTietGioHangRepository;
	@Autowired
	private ThanhToanService thanhToanService;
	@Autowired
	private GioHangRepository gioHangRepository;
	@Autowired
	private ChiTietGioHangService chiTietGioHangService;
	@Autowired
	private ThanhToanRepository thanhToanRepository;
	@Autowired
	private HoaDonRepository hoaDonRepository;

	@PostMapping("/add-giohang")
	public String addgiohang(@RequestParam Integer userId, @RequestParam Integer size, @RequestParam Float gia,
			@RequestParam Integer soluong, @RequestParam Integer productId) {
		Optional<User> user = userService.findById(userId);
		if (user.isPresent()) {
			List<GioHang> giohangList = user.get().getGioHangs();
			User userEntity = user.get();

			// Nếu người dùng chưa có giỏ hàng
			if (giohangList == null || giohangList.isEmpty()) {
				GioHang gioHang = new GioHang();
				Product product = productService.findById(productId);
				Float tongtien = gia * soluong;

				// Thêm chi tiết giỏ hàng
				gioHang.addDetail(gia, size, soluong, product, tongtien);
				gioHang.setTongtien(tongtien);
				gioHang.setUser(userEntity);
				sanPhamTonKhoService.updateTonKho(size, productId, 0, soluong);
				ThanhToan thanhToan = new ThanhToan();
				thanhToan.setGiohang(gioHang);
				thanhToan.setTrangthai(false);
				gioHang.setThanhtoan(thanhToan);
				// Lưu giỏ hàng và chi tiết giỏ hàng
				gioHangService.create(gioHang);
				thanhToanService.create(thanhToan);
				chiTietGioHangRepository.saveAll(gioHang.getChiTietGioHang());
			} else {
				// Giỏ hàng đã tồn tại, chỉ cần cập nhật giỏ hàng
				GioHang existingGioHang = giohangList.get(0);
				Product product = productService.findById(productId);

				// Tạo chi tiết giỏ hàng và thêm vào giỏ hàng hiện tại
				Float tonggiasanpham = gia * soluong;
				existingGioHang.addDetail(gia, size, soluong, product, tonggiasanpham);
				sanPhamTonKhoService.updateTonKho(size, productId, 0, soluong);
				// Cập nhật tổng tiền của giỏ hàng
				Float tongtien = (float) existingGioHang.getChiTietGioHang().stream()
						.mapToDouble(ChiTietGioHang::getTonggiasanpham).sum();
				existingGioHang.setTongtien(tongtien);

				gioHangService.create(existingGioHang);
				chiTietGioHangRepository.saveAll(existingGioHang.getChiTietGioHang());
			}
		}
		return "redirect:/shop";
	}

	@GetMapping("/giohang")
	public String cart(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		User user = userService.findById(50).get();
		List<GioHang> gioHang = gioHangRepository.findByUser(user);
		if (!gioHang.isEmpty()) {
			Set<ChiTietGioHang> chiTietGioHang = gioHang.get(0).getChiTietGioHang();
			Float totalPriceFloat = gioHang.get(0).getTongtien();
			BigDecimal totalPrice = new BigDecimal(totalPriceFloat).setScale(2, RoundingMode.HALF_UP);
			if (totalPrice.stripTrailingZeros().scale() <= 0) {
				totalPrice = totalPrice.setScale(0, RoundingMode.DOWN);
			}
			Integer giohangId = gioHang.get(0).getId();
			model.addAttribute("chiTietGioHang", chiTietGioHang);
			model.addAttribute("giohangId", giohangId);
			model.addAttribute("totalPrice", totalPrice);
		}
		return "user/cart";
	}

	@GetMapping("/delete-chitietgiohang/{id}")
	public String deletedetail(@PathVariable Integer id) {
		ChiTietGioHang chiTietGioHang = chiTietGioHangService.findById(id);
		Integer oldSoLuong = chiTietGioHang.getSoLuong();
		Integer product = chiTietGioHang.getProduct().getId();
		GioHang gioHang = chiTietGioHang.getGiohang();
		this.sanPhamTonKhoService.updateTonKho(chiTietGioHang.getSize(), product, oldSoLuong, 0);
		if (this.chiTietGioHangService.delete(id)) {
			Float totalAmount = gioHang.calculateTotal();
			gioHang.setTongtien((float) totalAmount);
			this.gioHangService.update(gioHang);
			return "redirect:/user/giohang";
		} else {
			return "redirect:/user/giohang";
		}
	}

	@GetMapping("/checkout/{id}")
	public String checkout(Model model, HttpSession session, @PathVariable Integer id,
			@RequestParam(required = false, defaultValue = "COD") String paymentMethod,
			@RequestParam(required = false, defaultValue = "/fe/images/bank.png") String qrCodeUrl) {
		model.addAttribute("qrCodeUrl", "/fe/images/bank.png");
		model.addAttribute("paymentMethod", paymentMethod);
		ThanhToan thanhToan = this.thanhToanRepository.findByGiohangId(id);
		if (thanhToan == null) {
			thanhToan = new ThanhToan();
		}
		model.addAttribute("thanhtoan", thanhToan);
		model.addAttribute("idGiohang", id);
		model.addAttribute("phuongthuc", PhuongThucThanhToan.values());
		Integer userId = (Integer) session.getAttribute("userId");
		User user = userService.findById(50).get();
		List<GioHang> gioHang = gioHangRepository.findByUser(user);
		if (!gioHang.isEmpty()) {
			Set<ChiTietGioHang> chiTietGioHang = gioHang.get(0).getChiTietGioHang();
			Float totalPriceFloat = gioHang.get(0).getTongtien();
			BigDecimal totalPrice = new BigDecimal(totalPriceFloat).setScale(2, RoundingMode.HALF_UP);
			if (totalPrice.stripTrailingZeros().scale() <= 0) {
				totalPrice = totalPrice.setScale(0, RoundingMode.DOWN);
			}
			Integer giohangId = gioHang.get(0).getId();
			model.addAttribute("chiTietGioHang", chiTietGioHang);
			model.addAttribute("giohangId", giohangId);
			model.addAttribute("totalPrice", totalPrice);
		}
		model.addAttribute("user", user);
		return "user/checkout";
	}

	@PostMapping("/confirm-thanhtoan")
	public String confirm(@ModelAttribute ThanhToan thanhtoan, @RequestParam Integer idGioHang, HttpSession session,
			Model model) {
		thanhtoan.setGiohang(this.gioHangService.findById(idGioHang));
		thanhtoan.setTrangthai(false);
		if (this.thanhToanService.update(thanhtoan)) {
			PhuongThucThanhToan phuongthuc = thanhtoan.getPhuongthuc();
			model.addAttribute("paymentMethod", phuongthuc.name());
			if (phuongthuc == PhuongThucThanhToan.BANK) {
				Integer userId = (Integer) session.getAttribute("userId");
				User user = userService.findById(50).get();
				List<GioHang> gioHang = gioHangRepository.findByUser(user);
				if (!gioHang.isEmpty()) {
					Set<ChiTietGioHang> chiTietGioHang = gioHang.get(0).getChiTietGioHang();
					Float totalPriceFloat = gioHang.get(0).getTongtien();
					BigDecimal totalPrice = new BigDecimal(totalPriceFloat).setScale(2, RoundingMode.HALF_UP);
					if (totalPrice.stripTrailingZeros().scale() <= 0) {
						totalPrice = totalPrice.setScale(0, RoundingMode.DOWN);
					}
					Integer giohangId = gioHang.get(0).getId();
					model.addAttribute("chiTietGioHang", chiTietGioHang);
					model.addAttribute("giohangId", giohangId);
					model.addAttribute("totalPrice", totalPrice);
				}
				return "redirect:/user/checkout/" + idGioHang + "?paymentMethod=" + phuongthuc.name() + "&qrCodeUrl="
						+ "";
			}
		}
		return "user/payment/cod";
	}

	@GetMapping("/hoadon")
	public String hoadon(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		User user = userService.findById(50).get();
		List<HoaDon> hoaDons = hoaDonRepository.findByUser(user);
		List<GioHang> gioHang = gioHangRepository.findByUser(user);
		if (!hoaDons.isEmpty() && !gioHang.isEmpty()) {
			Set<ChiTietHoaDon> chiTietHoaDons = hoaDons.get(0).getChiTietHoaDon();
			Float totalPriceFloatHD = hoaDons.get(0).getTongtien();
			BigDecimal totalPriceHD = new BigDecimal(totalPriceFloatHD).setScale(2, RoundingMode.HALF_UP);
			if (totalPriceHD.stripTrailingZeros().scale() <= 0) {
				totalPriceHD = totalPriceHD.setScale(0, RoundingMode.DOWN);
			}
			Integer hoadonId = hoaDons.get(0).getId();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a");
			List<Map<String, Object>> hoaDonWithDetails = hoaDons.stream().map(hoaDon -> {
				Map<String, Object> data = new HashMap<>();
				data.put("hoaDon", hoaDon);
				data.put("formattedNgaylap", hoaDon.getNgaylap().format(formatter));
				data.put("soLuongChiTiet", hoaDon.getChiTietHoaDon().size());
				return data;
			}).collect(Collectors.toList());
			model.addAttribute("hoaDonWithDetail", hoaDonWithDetails);
			model.addAttribute("chiTietHoaDon", chiTietHoaDons);
			model.addAttribute("hoadonId", hoadonId);
			model.addAttribute("totalPrice", totalPriceHD);
			Set<ChiTietGioHang> chiTietGioHang = gioHang.get(0).getChiTietGioHang();
			Float totalPriceFloat = gioHang.get(0).getTongtien();
			BigDecimal totalPrice = new BigDecimal(totalPriceFloat).setScale(2, RoundingMode.HALF_UP);
			if (totalPrice.stripTrailingZeros().scale() <= 0) {
				totalPrice = totalPrice.setScale(0, RoundingMode.DOWN);
			}
			Integer giohangId = gioHang.get(0).getId();
			model.addAttribute("chiTietGioHang", chiTietGioHang);
			model.addAttribute("giohangId", giohangId);
			model.addAttribute("totalPrice", totalPrice);
		}
		return "user/hoadon";
	}

	@GetMapping("/{hoadonId}/chitiet")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getChiTietHoaDon(@PathVariable Integer hoadonId) {
		try {
			HoaDon hoaDon = hoaDonRepository.findById(hoadonId)
					.orElseThrow(() -> new RuntimeException("HoaDon không tồn tại"));

			Set<ChiTietHoaDon> chiTietHoaDons = hoaDon.getChiTietHoaDon();
			List<Map<String, Object>> chiTietList = new ArrayList<>();
			for (ChiTietHoaDon chiTiet : chiTietHoaDons) {
				Map<String, Object> chiTietData = new HashMap<>();
				chiTietData.put("productName", chiTiet.getProduct().getName());
				chiTietData.put("soluong", chiTiet.getSoluong());
				chiTietData.put("gia", chiTiet.getGia());
				chiTietData.put("tonggiasanpham", chiTiet.getTonggiasanpham());
				chiTietList.add(chiTietData);
			}

			Map<String, Object> response = new HashMap<>();
			response.put("chiTietHoaDons", chiTietList);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("error", "Có lỗi xảy ra khi xử lý yêu cầu."));
		}
	}

	@PostMapping("/giohang")
	public String updateCartItem(@RequestParam Integer chitietId, @RequestParam Integer newSoluong,
			@RequestParam Integer productId, @RequestParam Integer size) {
		// Giả sử bạn có service để cập nhật giỏ hàng
		ChiTietGioHang chiTietGioHang = chiTietGioHangRepository.findById(chitietId).get();
		Integer soluong = chiTietGioHang.getSoLuong();
		chiTietGioHang.setSoLuong(newSoluong);
		chiTietGioHangService.update(chiTietGioHang);
		sanPhamTonKhoService.updateTonKho(size, productId, soluong, newSoluong);
		// Redirect lại trang giỏ hàng sau khi cập nhật
		return "redirect:/user/giohang"; // Thay đổi URL nếu cần
	}

}
