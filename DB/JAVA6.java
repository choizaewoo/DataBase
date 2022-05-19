package DB;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JAVA6 {
	public static String k42_subStrByte(String k42_source, int k42_cutLength) {
		
		if (!k42_source.isEmpty()) { 
			k42_source = k42_source.trim(); 
		
			if (k42_source.getBytes().length < k42_cutLength) {
				
				for (int k42_i = k42_cutLength - k42_source.getBytes().length; k42_i > 0; k42_i--) {
					//공백을 추가해준다.
					k42_source += " ";
				}
				
				return k42_source;
			} else {
				
				StringBuffer k42_sb = new StringBuffer(k42_cutLength);
				
				int k42_cnt = 0;
			
				for (char k42_ch : k42_source.toCharArray()) {
					
					k42_cnt += String.valueOf(k42_ch).getBytes().length;
				
					if (k42_cnt > k42_cutLength)
						//브레이크문으로 나간다
						break;
				
					k42_sb.append(k42_ch);
				}
			
			

				if (k42_sb.toString().getBytes().length == k42_cutLength - 1) {

					k42_sb.append(" ");
				}

				return k42_sb.toString();
			}
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
	
		SimpleDateFormat k42_sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar k42_c1 = Calendar.getInstance();
		String k42_strToday = k42_sdf.format(k42_c1.getTime());

		DecimalFormat k42_df = new DecimalFormat("###,###,###,###,###");

		String[] k42_itemName = { "칸초", "미니언즈 젤리", "미니언즈 오렌지", "갤럭시버즈", "에어팟", "치킨", "TV", "삼다수 2L", "찰떡아이스크림",
				"베스킨라빈스31", "닭가슴살 6개입", "소고기맛 치킨너겟", "블루베리스무디", "케찹맛 머스타드", "뽀로로맥주6개입", "프리미엄요거트스무디", "비비고비빔국수",
				"크랜베리 콘푸로스트", "바나나우유10개입", "진라면소컵(매)65g*6", "피로회복 자양강장제 박카스5개입", "홈런볼 민트초코", "불닭볶음면 5개입", "CJ 비비고칩 20G",
				"흑원당 말차 밀크티", "스타벅스 커피빈", "프리미엄한우1등급채끝살"};
		int[] k42_price = { 2000, 6900, 5980, 3300, 255500, 3380, 66666610, 2750, 1080, 4980, 2480, 7920, 4980, 4980,
				7130, 3980, 4480, 4980, 3210, 6950, 2980, 1990, 1980, 3980, 4480, 1280, 1227980};
		int[] k42_amount = { 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1 };
		boolean[] k42_taxFree = { false, false, true, false, false, false, false, false, false, false, false, true,
				false, true, true, false, false, false, false, false, false, false, false, false, false, false, false };
		int k42_totalSum = 0;
		int k42_yesTaxFree = 0; 
		int k42_noTaxFree = 0; 
		int k42_point = 0; 
		int k42_alreadyPoint = 5473; 
		int k42_afterPoint = 0; 

		int supplyPrice = 0; 
		k42_point = k42_totalSum / 10000;
		k42_afterPoint = k42_alreadyPoint + k42_point;
		
		int k42_getSu = k42_itemName.length; 
	
		
		System.out.printf("\n");
		System.out.printf("     %s", "emart"); 
		System.out
				.printf("    이마트 죽전점 (031)888-1234 \n              206-26-50913 강희석\n              용인 수지구 포은대로 552\n");
		System.out.printf("\n");
		System.out.printf("영수증 미지참시 교환/환불 불가\n");
		
		System.out.printf("정상상품에 한함, 30일 이내(신선 7일)\n");
		

		System.out.printf("※일부 브랜드매장 제외(매장 고지물참조)\n");		
		
		System.out.printf("교환/환불 구매점에서 가능(결제카드 지참)\n");
	
		System.out.printf("\n");
		Calendar k42_calt = Calendar.getInstance();
		SimpleDateFormat k42_sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm"); 
		SimpleDateFormat k42_sdtt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); 
		//calendar 클래스를 통하여 시간을 출력해준다.
		System.out.printf("%s%s %17s\n", "[구 매]", k42_sdt.format(k42_calt.getTime()), "POS:0011-9861");
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%7s %15s %2s %5s \n", "상 품 명", "단 가", "수량", "금 액");
		

		for (int k42_i = 0; k42_i < k42_itemName.length; k42_i++) {
			
			if (k42_i % 5 == 0) {
				System.out.printf("-----------------------------------------\n");
			}
		
			int k42_sum = k42_price[k42_i] * k42_amount[k42_i];
		
			if (k42_taxFree[k42_i] == true) { // 면세물품이면
				k42_yesTaxFree += k42_price[k42_i]; // 가격을 면세품목에 추가한다
		
		
				System.out.printf("* %6s%10s%2d%11s\n", k42_subStrByte(k42_itemName[k42_i], 16),
						k42_df.format(k42_price[k42_i]), k42_amount[k42_i], k42_df.format(k42_sum));
			} else if (k42_taxFree[k42_i] == false) { // 면세물품이 아니면
			
				k42_noTaxFree += k42_price[k42_i] * k42_amount[k42_i]; // 과세물품가격에 가격을 추가한다.
				
				
				System.out.printf("  %6s%10s%2d%11s\n", k42_subStrByte(k42_itemName[k42_i], 16),
						k42_df.format(k42_price[k42_i]), k42_amount[k42_i], k42_df.format(k42_sum));
			}
		
			supplyPrice = (int) (k42_noTaxFree / 1.1); 
		
			k42_totalSum = k42_yesTaxFree + k42_noTaxFree;
			
			
		}
		
		System.out.printf("\n");
		
		System.out.printf("%22s %13d\n", "총 품목 수량", k42_getSu);
		
		System.out.printf("%23s %13s\n", "(*)면세    물품", k42_df.format(k42_yesTaxFree));
		
		System.out.printf("%23s %13s\n", "과세    물품", k42_df.format(supplyPrice));
		
		System.out.printf("%24s %13s\n", "부   가   세", k42_df.format(k42_noTaxFree - supplyPrice));
		
		
		System.out.printf("%25s %13s\n", "합        계", k42_df.format(k42_totalSum));
		
		
		System.out.printf("%s %23s\n", "결 제 대 상 금 액", k42_df.format(k42_totalSum));
		
		System.out.printf("-----------------------------------------\n");
		System.out.printf("0012 KEB 하나          54177*45/465461213\n");
		
		System.out.printf("%s%25s%s\n","카드결제(IC)","일시불 /", k42_df.format(k42_totalSum));
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%22s\n", "[신세계포인트 적립]");
		System.out.printf("홍*두 고객님의 포인트 현황입니다.\n");
		
		System.out.printf("%s%20s%7s\n", "금회발생포인트","94546**11111", k42_df.format(k42_point));
		System.out.printf("%s %15s(%7s)\n", "누계(가용)포인트", k42_df.format(k42_afterPoint),
				k42_df.format(k42_alreadyPoint));
		System.out.printf("*신세계포인트 유효기간은 2년입니다.\n");
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%22s\n", "구매기준 무료주차시간 자동부여");
		System.out.printf("차량번호 :                       34저****\n");
		
		System.out.printf("%s%31s\n","입차시간 :",k42_sdtt.format(k42_calt.getTime()));
		System.out.printf("-----------------------------------------\n");
		System.out.printf("캐셔:084599 양00                     1150\n");
		System.out.printf("      ||||||||||||||||||||||||||||||||  \n");
		System.out.printf("      ||||||||||||||||||||||||||||||||  \n");

		System.out.printf("%16s%s", k42_strToday, "/0011123/00164890/31");

	}

	public static String cutString(String k42_a) {

		String cut = k42_a.substring(0, 14);
		return cut;
	}

}
