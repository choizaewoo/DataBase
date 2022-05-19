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
					//������ �߰����ش�.
					k42_source += " ";
				}
				
				return k42_source;
			} else {
				
				StringBuffer k42_sb = new StringBuffer(k42_cutLength);
				
				int k42_cnt = 0;
			
				for (char k42_ch : k42_source.toCharArray()) {
					
					k42_cnt += String.valueOf(k42_ch).getBytes().length;
				
					if (k42_cnt > k42_cutLength)
						//�극��ũ������ ������
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

		String[] k42_itemName = { "ĭ��", "�̴Ͼ��� ����", "�̴Ͼ��� ������", "�����ù���", "������", "ġŲ", "TV", "��ټ� 2L", "�������̽�ũ��",
				"����Ų���31", "�߰����� 6����", "�Ұ��� ġŲ�ʰ�", "��纣��������", "������ �ӽ�Ÿ��", "�Ƿηθ���6����", "�����̾����Ʈ������", "����������",
				"ũ������ ��Ǫ�ν�Ʈ", "�ٳ�������10����", "��������(��)65g*6", "�Ƿ�ȸ�� �ھ簭���� ��ī��5����", "Ȩ���� ��Ʈ����", "�Ҵߺ����� 5����", "CJ ����Ĩ 20G",
				"����� ���� ��ũƼ", "��Ÿ���� Ŀ�Ǻ�", "�����̾��ѿ�1���ä����"};
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
				.printf("    �̸�Ʈ ������ (031)888-1234 \n              206-26-50913 ����\n              ���� ������ ������� 552\n");
		System.out.printf("\n");
		System.out.printf("������ �������� ��ȯ/ȯ�� �Ұ�\n");
		
		System.out.printf("�����ǰ�� ����, 30�� �̳�(�ż� 7��)\n");
		

		System.out.printf("���Ϻ� �귣����� ����(���� ����������)\n");		
		
		System.out.printf("��ȯ/ȯ�� ���������� ����(����ī�� ����)\n");
	
		System.out.printf("\n");
		Calendar k42_calt = Calendar.getInstance();
		SimpleDateFormat k42_sdt = new SimpleDateFormat("YYYY-MM-dd HH:mm"); 
		SimpleDateFormat k42_sdtt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); 
		//calendar Ŭ������ ���Ͽ� �ð��� ������ش�.
		System.out.printf("%s%s %17s\n", "[�� ��]", k42_sdt.format(k42_calt.getTime()), "POS:0011-9861");
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%7s %15s %2s %5s \n", "�� ǰ ��", "�� ��", "����", "�� ��");
		

		for (int k42_i = 0; k42_i < k42_itemName.length; k42_i++) {
			
			if (k42_i % 5 == 0) {
				System.out.printf("-----------------------------------------\n");
			}
		
			int k42_sum = k42_price[k42_i] * k42_amount[k42_i];
		
			if (k42_taxFree[k42_i] == true) { // �鼼��ǰ�̸�
				k42_yesTaxFree += k42_price[k42_i]; // ������ �鼼ǰ�� �߰��Ѵ�
		
		
				System.out.printf("* %6s%10s%2d%11s\n", k42_subStrByte(k42_itemName[k42_i], 16),
						k42_df.format(k42_price[k42_i]), k42_amount[k42_i], k42_df.format(k42_sum));
			} else if (k42_taxFree[k42_i] == false) { // �鼼��ǰ�� �ƴϸ�
			
				k42_noTaxFree += k42_price[k42_i] * k42_amount[k42_i]; // ������ǰ���ݿ� ������ �߰��Ѵ�.
				
				
				System.out.printf("  %6s%10s%2d%11s\n", k42_subStrByte(k42_itemName[k42_i], 16),
						k42_df.format(k42_price[k42_i]), k42_amount[k42_i], k42_df.format(k42_sum));
			}
		
			supplyPrice = (int) (k42_noTaxFree / 1.1); 
		
			k42_totalSum = k42_yesTaxFree + k42_noTaxFree;
			
			
		}
		
		System.out.printf("\n");
		
		System.out.printf("%22s %13d\n", "�� ǰ�� ����", k42_getSu);
		
		System.out.printf("%23s %13s\n", "(*)�鼼    ��ǰ", k42_df.format(k42_yesTaxFree));
		
		System.out.printf("%23s %13s\n", "����    ��ǰ", k42_df.format(supplyPrice));
		
		System.out.printf("%24s %13s\n", "��   ��   ��", k42_df.format(k42_noTaxFree - supplyPrice));
		
		
		System.out.printf("%25s %13s\n", "��        ��", k42_df.format(k42_totalSum));
		
		
		System.out.printf("%s %23s\n", "�� �� �� �� �� ��", k42_df.format(k42_totalSum));
		
		System.out.printf("-----------------------------------------\n");
		System.out.printf("0012 KEB �ϳ�          54177*45/465461213\n");
		
		System.out.printf("%s%25s%s\n","ī�����(IC)","�Ͻú� /", k42_df.format(k42_totalSum));
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%22s\n", "[�ż�������Ʈ ����]");
		System.out.printf("ȫ*�� ������ ����Ʈ ��Ȳ�Դϴ�.\n");
		
		System.out.printf("%s%20s%7s\n", "��ȸ�߻�����Ʈ","94546**11111", k42_df.format(k42_point));
		System.out.printf("%s %15s(%7s)\n", "����(����)����Ʈ", k42_df.format(k42_afterPoint),
				k42_df.format(k42_alreadyPoint));
		System.out.printf("*�ż�������Ʈ ��ȿ�Ⱓ�� 2���Դϴ�.\n");
		System.out.printf("-----------------------------------------\n");
		System.out.printf("%22s\n", "���ű��� ���������ð� �ڵ��ο�");
		System.out.printf("������ȣ :                       34��****\n");
		
		System.out.printf("%s%31s\n","�����ð� :",k42_sdtt.format(k42_calt.getTime()));
		System.out.printf("-----------------------------------------\n");
		System.out.printf("ĳ��:084599 ��00                     1150\n");
		System.out.printf("      ||||||||||||||||||||||||||||||||  \n");
		System.out.printf("      ||||||||||||||||||||||||||||||||  \n");

		System.out.printf("%16s%s", k42_strToday, "/0011123/00164890/31");

	}

	public static String cutString(String k42_a) {

		String cut = k42_a.substring(0, 14);
		return cut;
	}

}
