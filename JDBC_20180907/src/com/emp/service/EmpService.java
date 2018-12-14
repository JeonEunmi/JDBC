package com.emp.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.emp.domain.Departments;
import com.emp.domain.Employees;
import com.emp.domain.Jobs;
import com.emp.domain.Login;
import com.emp.domain.Regions;
import com.emp.persistance.DepartmentDAO;
import com.emp.persistance.EmpDAO;
import com.emp.persistance.JobDAO;
import com.emp.persistance.LoginDAO;
import com.emp.persistance.RegionDAO;

// ���� �޴��� �׼� Ŭ����
public class EmpService {

	private String id;
	private LoginDAO loginDAO = new LoginDAO();
	private RegionDAO regionDAO = new RegionDAO();
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private JobDAO jobDAO = new JobDAO();
	private EmpDAO empDAO = new EmpDAO();

	public void login(Scanner sc) {
		/*
		 * ���̵�>admin �н�����>1111 ������ 'admin' �α��εǾ����ϴ�.
		 */

		System.out.print("���̵�> ");
		String id = sc.nextLine();

		System.out.print("�н�����> ");
		String pw = sc.nextLine();

		int result = this.loginDAO.login(new Login(id, pw));

		if (result == 1) {
			this.id = id;
			System.out.printf("������ '%s' �α��εǾ����ϴ�.", id);
			this.main(sc);
		} else {
			System.out.println("���̵� �Ǵ� �н����尡 Ʋ�Ƚ��ϴ�.");
		}

	}

	// 1.�������� 2.������������ 3.�������������� 0. �α׾ƿ�
	private void main(Scanner sc) {

		/*
		 * -------------- �������� v2.0 (������:admin) -------------- 1.�������� 2.������������ 3.��������������
		 * ����>
		 */

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� v2.0 (������:%s)%n", this.id);
			System.out.println("--------------");
			System.out.println("1.��������  2.������������  3.��������������");
			System.out.print("����(����: 0)>");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				System.out.printf("������ %s �α׾ƿ� �Ǿ����ϴ�.", this.id);
				System.out.println();
				break;
			}

			switch (m) {
			case 1:
				this.menu1(sc);
				break;
			case 2:
				this.menu2(sc);
				break;
			case 3:
				this.menu3(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				System.out.println();
				break;
			}
		}
	}

	// -----------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ���� ����
	// 1.�����Է� 2.������ü��� 3.�����˻� 4.��������
	private void menu1(Scanner sc) {

		while (true) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("--------------");
			System.out.printf("�������� v2.0 (������:%s) > ���� ���� %n", this.id);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.������ü���  3.�����˻�  4.��������");
			System.out.print("����(����: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu1_sub1(sc);
				break;
			case 2:
				this.menu1_sub2(sc);
				break;
			case 3:
				this.menu1_sub3(sc);
				break;
			case 4:
				this.menu1_sub4(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > ���� �Է�
	private void menu1_sub1(Scanner sc) {

		System.out.println();
		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� �Է�%n", id);
		System.out.println("--------------");

		String date = null;
		System.out.print("�̸�> ");
		String name = sc.nextLine();

		String regExp1 = "\\d{6}-\\d{7}";

		while (true) {

			System.out.print("�ֹι�ȣ(YYMMDD-NNNNNNN)> ");
			String ssn = sc.nextLine();

			if (Pattern.matches(regExp1, ssn) == false) {
				System.out.println();
				System.out.println("���ֹι�ȣ ���Ŀ� �°� �Է��ϼ���.");

			} else {

				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				while (true) {
					sf.setLenient(false);

					System.out.print("�Ի���(YYYY-MM-DD)> ");
					date = sc.nextLine();

					try {
						sf.parse(date);
						break;
					} catch (Exception e1) {
						// Ʋ�� ��¥�� ��쿹�ܹ߻�
						System.out.println();
						System.out.println("����¥ ���Ŀ� �°� �Է��ϼ���(yyyy-MM-dd) \n");
					}
				}

				while (true) {

					String regExp2 = "(010)-\\d{3,4}-\\d{4}";

					System.out.print("��ȭ��ȣ(010-XXXX-XXXX)> ");
					String phone = sc.nextLine();

					if (Pattern.matches(regExp2, phone) == false) {
						System.out.println();
						System.out.println("����ó ������ �����ּ���. (010-xxxx-xxxx)");
					} else {

						System.out.println("--------------");
						System.out.println("������ȣ / ������");

						List<Regions> r = this.regionDAO.list1();

						for (Regions region : r) {
							System.out.println(region.print1());
						}
						System.out.println("--------------");

						while (true) {

							String regExp3 = "(REG)\\d{2}";

							System.out.print("������ȣ> ");
							String regId = sc.nextLine();

							if (Pattern.matches(regExp3, regId) == false) {
								System.out.println();
								System.out.println("������ȣ ������ �ƴմϴ�.");
							} else {
								System.out.println("--------------");
								System.out.println("�μ���ȣ / �μ���");

								List<Departments> d = this.departmentDAO.list1();

								for (Departments dep : d) {
									System.out.println(dep.print1());
								}

								System.out.println("--------------");

								while (true) {

									String regExp4 = "(DEPT)\\d{2}";

									System.out.print("�μ���ȣ> ");
									String deptId = sc.nextLine();

									if (Pattern.matches(regExp4, deptId) == false) {
										System.out.println();
										System.out.println("�μ���ȣ ������ �ƴմϴ�.");
									} else {

										System.out.println("--------------");
										System.out.println("������ȣ / ������ / �ּұ⺻��");

										List<Jobs> j = this.jobDAO.list1();

										for (Jobs job : j) {
											System.out.println(job.print1());
										}

										System.out.println("--------------");

										while (true) {

											String regExp5 = "(JOB)\\d{2}";

											System.out.print("������ȣ> ");
											String jobId = sc.nextLine();

											if (Pattern.matches(regExp5, jobId) == false) {
												System.out.println();
												System.out.println("������ȣ ������ �ƴմϴ�.");
											} else {
												System.out.print("�⺻��> ");
												int basicPay = sc.nextInt();
												sc.nextLine();

												System.out.print("����> ");
												int extraPay = sc.nextInt();
												sc.nextLine();

												int pay = basicPay + extraPay;

												int result = this.empDAO.employeeAdd(new Employees(null, name, ssn,
														Date.valueOf(date), phone, null, null, null, regId, deptId,
														jobId, basicPay, extraPay, pay));

												System.out.printf("%s ���� �Է��� �Ϸ�Ǿ����ϴ�.", result);

												break;
											}
										}
										break;
									}
								}
								break;
							}

						}
						break;
					}
				}
				break;
			}

		}
	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > ������ü���
	private void menu1_sub2(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ���%n", id);
		System.out.println("--------------");
		System.out.println("1.�������  2.�̸�����  3.����������  4.�μ�������  5.����������");
		System.out.print("����> ");

		int num = sc.nextInt();
		sc.nextLine();

		switch (num) {
		case 1:
			this.menu1_sub2_sub1();
			break;
		case 2:
			this.menu1_sub2_sub2();
			break;
		case 3:
			this.menu1_sub2_sub3();
			break;
		case 4:
			this.menu1_sub2_sub4();
			break;
		case 5:
			this.menu1_sub2_sub5();
			break;
		default:
			break;

		}

	}

	private void menu1_sub2_sub1() {

		List<Employees> emp = this.empDAO.list1(1);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ��� > �������%n", id);

		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	private void menu1_sub2_sub2() {

		List<Employees> emp = this.empDAO.list1(2);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ��� > �̸� ����%n", id);

		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	private void menu1_sub2_sub3() {

		List<Employees> emp = this.empDAO.list1(3);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ��� > ������ ����%n", id);

		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	private void menu1_sub2_sub4() {

		List<Employees> emp = this.empDAO.list1(4);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ��� > �μ��� ����%n", id);

		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	private void menu1_sub2_sub5() {

		List<Employees> emp = this.empDAO.list1(5);

		System.out.println();
		System.out.println("--------------");

		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ��ü ��� > ������ ����%n", id);

		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > �����˻�
	private void menu1_sub3(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� �˻�%n", id);
		System.out.println("--------------");
		System.out.println("1.����˻�  2.�̸��˻�  3.������˻�  4.�μ���˻�  5.������˻�");
		System.out.print("����> ");

		int key = sc.nextInt();
		sc.nextLine();

		System.out.print("�˻��ܾ�> ");
		String value = sc.nextLine();

		List<Employees> emp = this.empDAO.list2(key, value);
		System.out.println();
		System.out.println("--------------");
		System.out.printf("��ü �ο� : %s��%n", emp.size());
		System.out.println("--------------");
		System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

		for (Employees e : emp) {
			System.out.println(e);
		}

		System.out.println();

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� > ��������
	private void menu1_sub4(Scanner sc) {

		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� > ���� ����%n", id);
		System.out.println("--------------");
		System.out.print("�������̵�(EMPXXX)> ");
		String eid = sc.nextLine();

		List<Employees> emp = this.empDAO.list2(1, eid);

		if (emp.size() != 0) {
			System.out.println();
			System.out.println("--------------");
			System.out.println("��� / �̸� / �ֹι�ȣ / �Ի��� / ��ȭ��ȣ / ������ / �μ��� / ������ / �⺻�� / ���� / �޿�");

			for (Employees e : emp) {
				System.out.println(e);
			}

			System.out.println();

			System.out.println("���� �����Ͻðڽ��ϱ�?(0/1) ");

			int num = sc.nextInt();
			sc.nextLine();

			if (num == 1) {
				int result = this.empDAO.empDelete(eid);

				System.out.println("*");
				System.out.printf("%s���� ������ ������ �Ϸ�Ǿ����ϴ�.! %n", result);

			} else if (num == 0) {
				System.out.println("������ ��ҵǾ����ϴ�.");
			} else {
				System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�!");
			}

		} else {
			System.out.println("�˻������ �����ϴ�.");
		}

		System.out.println();

	}

	// --------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ���� ���� ����
	// 1.�������� 2.�μ����� 3.��������
	private void menu2(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ����%n", id);
			System.out.println("--------------");
			System.out.println("1.��������  2.�μ�����  3.��������");
			System.out.print("����(����: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu2_sub1(sc);
				break;
			case 2:
				this.menu2_sub2(sc);
				break;
			case 3:
				this.menu2_sub3(sc);
				break;
			case 4:
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	// ���� ���� v2.0 (������:admin) > ���� ���� ���� > ��������
	private void menu2_sub1(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > ��������%n", id);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.�������  3.��������");
			System.out.print("����(����: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu2_sub1_sub1(sc);
				break;
			case 2:
				this.menu2_sub1_sub2();
				break;
			case 3:
				this.menu2_sub1_sub3(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	private void menu2_sub1_sub1(Scanner sc) {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� �Է�%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������");

		List<Regions> region = this.regionDAO.list1();

		for (Regions r : region) {
			System.out.println(r.print1());
		}

		System.out.println();
		System.out.print("�ű� ���� �̸�> ");
		String regName = sc.nextLine();

		int result = this.regionDAO.regionAdd(new Regions(null, regName));

		System.out.printf("%s���� �ű� ���� ���� �Է� �Ϸ�!", result);

	}

	private void menu2_sub1_sub2() {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� ���%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������");

		List<Regions> region = this.regionDAO.list1();

		for (Regions r : region) {
			System.out.println(r.print1());
		}

	}

	private void menu2_sub1_sub3(Scanner sc) {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� ����%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������ / �������ɿ���");

		List<Regions> region = this.regionDAO.list2();

		for (Regions r : region) {
			System.out.println(r.print2());
		}
		System.out.println("--------------");
		System.out.println();
		System.out.print("������ȣ> ");
		String rid = sc.nextLine();

		int result = this.regionDAO.regionDelete(rid);

		if (result > 0) {

			System.out.printf("%s ������ �����Ǿ����ϴ�.%n", rid);
		} else {
			System.out.println("�������� ���� ! ������ȣ ����");
		}

	}

	// -----------------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ���� ���� ���� > �μ�����
	private void menu2_sub2(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �μ� ����%n", id);
			System.out.println("--------------");
			System.out.println("1.�μ��Է�  2.�μ����  3.�μ�����");
			System.out.print("����(����: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu2_sub2_sub1(sc);
				break;
			case 2:
				this.menu2_sub2_sub2();
				break;
			case 3:
				this.menu2_sub2_sub3(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	private void menu2_sub2_sub1(Scanner sc) {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �μ����� > �μ� �Է�%n", id);
		System.out.println("--------------");
		System.out.println("�μ���ȣ / �μ���");

		List<Departments> dept = this.departmentDAO.list1();

		for (Departments d : dept) {
			System.out.println(d.print1());
		}

		System.out.println();
		System.out.print("�ű� �μ� �̸�> ");
		String deptName = sc.nextLine();

		int result = this.departmentDAO.depAdd(new Departments(null, deptName));

		System.out.printf("%s���� �ű� �μ� ���� �Է� �Ϸ�!%n", result);

	}

	private void menu2_sub2_sub2() {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �μ����� > �μ� ���%n", id);
		System.out.println("--------------");
		System.out.println("�μ���ȣ / �μ���");

		List<Departments> dept = this.departmentDAO.list1();

		for (Departments d : dept) {
			System.out.println(d.print1());
		}

	}

	private void menu2_sub2_sub3(Scanner sc) {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �μ����� > �μ� ����%n", id);
		System.out.println("--------------");
		System.out.println("�μ���ȣ / �μ��� / �������ɿ���");

		List<Departments> dept = this.departmentDAO.list2();

		for (Departments d : dept) {
			System.out.println(d.print2());
		}
		System.out.println("--------------");
		System.out.println();
		System.out.print("�μ���ȣ> ");
		String did = sc.nextLine();

		int result = this.departmentDAO.depDelete(did);

		if (result > 0) {
			System.out.printf("%s �μ��� �����Ǿ����ϴ�.%n", did);
		} else {
			System.out.println("�μ����� ����! �μ���ȣ ����");
		}

	}

	// ----------------------------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ���� ���� ���� > ��������
	private void menu2_sub3(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > ��������%n", id);
			System.out.println("--------------");
			System.out.println("1.�����Է�  2.�������  3.��������");
			System.out.print("����(����: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu2_sub3_sub1(sc);
				break;
			case 2:
				this.menu2_sub3_sub2();
				break;
			case 3:
				this.menu2_sub3_sub3(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	private void menu2_sub3_sub1(Scanner sc) {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� �Է�%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������");

		List<Jobs> job = this.jobDAO.list1();

		for (Jobs j : job) {
			System.out.println(j.print1());
		}

		System.out.println();
		System.out.print("�ű� ���� �̸�> ");
		String jobTitle = sc.nextLine();
		System.out.print("�ּ� �޿�> ");
		int minPay = sc.nextInt();
		sc.nextLine();

		int result = this.jobDAO.jobAdd(new Jobs(null, jobTitle, minPay));

		System.out.printf("%s���� �ű� ���� ���� �Է� �Ϸ�!%n", result);

	}

	private void menu2_sub3_sub2() {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� ���%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������");

		List<Jobs> job = this.jobDAO.list1();

		for (Jobs j : job) {
			System.out.println(j.print1());
		}

	}

	private void menu2_sub3_sub3(Scanner sc) {

		System.out.println();
		System.out.println("--------------");
		System.out.printf("���� ���� v2.0 (������:%s) > ���� ���� ���� > �������� > ���� ����%n", id);
		System.out.println("--------------");
		System.out.println("������ȣ / ������ / �ּ� �޿� / �������ɿ���");

		List<Jobs> job = this.jobDAO.list2();

		for (Jobs j : job) {
			System.out.println(j.print2());
		}

		System.out.println("--------------");
		System.out.println();
		System.out.print("������ȣ> ");
		String jid = sc.nextLine();

		int result = this.jobDAO.jobDelete(jid);

		if (result > 0) {
			System.out.printf("%s ������ �����Ǿ����ϴ�.%n", jid);
		} else {
			System.out.println("������������! ������ȣ����");
		}

	}

	// --------------------------------------------------------------------------

	// ���� ���� v2.0 (������:admin) > ��������������
	// 1.�������߰� 2.�н����庯��
	private void menu3(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.println("--------------");
			System.out.printf("���� ���� v2.0 (������:%s) > ��������������%n", id);
			System.out.println("--------------");
			System.out.println("1.�������߰� 2.�н����庯��");
			System.out.print("����(����: 0)> ");

			int m = sc.nextInt();
			sc.nextLine();

			if (m == 0) {
				break;
			}

			switch (m) {
			case 1:
				this.menu3_sub1(sc);
				break;
			case 2:
				this.menu3_sub2(sc);
				break;
			default:
				System.out.println("�߸��� �����Դϴ�.");
				break;
			}
		}

	}

	// ���� ���� v2.0 (������:admin) > �������������� > �������߰�
	private void menu3_sub1(Scanner sc) {

		System.out.print("���̵�> ");
		String adminId = sc.nextLine();

		System.out.print("�н�����> ");
		String password = sc.nextLine();

		int result = this.loginDAO.add(new Login(adminId, password));

		if (result > 0) {
			System.out.printf("������ '%s'�� �����Ǿ����ϴ�.", adminId);
		} else {
			System.out.println("������ ���� ����");
		}
	}

	// ���� ���� v2.0 (������:admin) > �������������� > �н����庯��
	private void menu3_sub2(Scanner sc) {

		System.out.print("�����н�����> ");
		String password = sc.nextLine();

		System.out.print("�ű��н�����> ");
		String newPassword = sc.nextLine();

		int result = this.loginDAO.modify(new Login(this.id, password, newPassword));

		if (result > 0) {
			System.out.printf("������ '%s'�� �н����尡 ����Ǿ����ϴ�.", this.id);
		} else {
			System.out.println("�н����� ���� �Ұ�");
		}
	}
}
