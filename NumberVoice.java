

class NumberVoice {

	/**
	 * String値を返します。
	 * 
	 * @param number Long値
	 * @return 引数をカタカナにした文字列
	 */
	public static String getNumberVoiceString(Long number) {
		StringBuilder resultStringBuilder = new StringBuilder() ;		

		short[] fourDigitNumberArray = makeFourDigitNumberArray(number);

		for (int fourDigitIndex = fourDigitNumberArray.length - 1; fourDigitIndex >= 0; fourDigitIndex--) {

			short[] numberArray = makeNumberArray(fourDigitNumberArray[fourDigitIndex]);

			byte continueZeroCount = countZero(numberArray);

			boolean isExistsFourDigitPosition = false;	// 4桁毎の位があるかどうか

			for (int numberArrayIndex = 0; numberArrayIndex < numberArray.length; numberArrayIndex++) {
				if (!isZero(numberArray[numberArrayIndex])) {
					isExistsFourDigitPosition = true;

					resultStringBuilder.append(getNumberString(numberArray[numberArrayIndex], numberArrayIndex, fourDigitIndex));

					resultStringBuilder.append(getPositionString(numberArray[numberArrayIndex], numberArrayIndex, fourDigitIndex, continueZeroCount));
				}
			}

			if (isExistsFourDigitPosition) {
				resultStringBuilder.append(getFourDigitPositionString(fourDigitIndex));
			}
		}

		return resultStringBuilder.toString() ;
	}

	/**
	 * 0が上の桁から何回続いているかを数えます。
	 * 
	 * @param numberArray 数の配列
	 * @return 0が何回か
	 */
	private static byte countZero(short[] numberArray) {
		byte continueZeroCount = 0;
		for (int i = numberArray.length - 1; i >= 0; i--) {
			if (numberArray[i] == 0) {
				continueZeroCount++;
			} else {
				break;
			}
		}
		return continueZeroCount;
	}

	/**
	 * 十、百、千の位を文字列（String）で返します。
	 * 
	 * @param number 位の数
	 * @param numberArrayIndex 位のインデックス。0は千
	 * @param fourDigitIndex 4桁毎の数のインデックス
	 * @param continueZeroCount 4桁毎の数が上の桁から何回0があるか
     * @return 十、百、千の位の文字列
	 */
	private static String getPositionString(short number, int numberArrayIndex, int fourDigitIndex, byte continueZeroCount) {
		String resultString = "" ;
		switch (numberArrayIndex) {
		case 0:
			if (number == 3) {
				resultString = "ゼン";
			} else {
				resultString = "セン";
			}
			break;
		case 1:
			if (fourDigitIndex == 4 && continueZeroCount == 2) {
				if (number == 3) {
					resultString = "ビャッ";
				} else if (number == 6
						|| number == 8) {
					resultString = "ピャッ";
				} else {
					resultString = "ヒャッ";
				}
			} else if (number == 3) {
				resultString = "ビャク";
			} else if (number == 6
					|| number == 8) {
				resultString = "ピャク";
			} else {
				resultString = "ヒャク";
			}
			break;
		case 2:
			if ((fourDigitIndex == 4 || fourDigitIndex == 3) && continueZeroCount == 1) {
				resultString = "ジッ";
			} else {
				resultString = "ジュウ";
			}
			break;
		case 3:
			break;
		default:
			break;
		}
		return resultString ;
	}

	private static boolean isZero(int num) {
		return num == 0;
	}

	/**
	 * 数を文字列（String）で返します。
	 * 
	 * @param number 数
	 * @param position 一から千の位までの位。 0は千
	 * @param fourDigitIndex 4桁毎の位。5はガイ
     * @return 数の文字列
	 */
	private static String getNumberString(short number, int position, int fourDigitIndex) {
		String resultString = "" ;
		switch (number) {
		case 1:
			if (position == 0 && fourDigitIndex >= 1) {
				resultString = "イッ";
			}

			if (position == 3) {
				if (fourDigitIndex == 3 || fourDigitIndex == 4) {
					resultString = "イッ";
				} else {
					resultString = "イチ";
				}
			}
			break;
		case 2:
			resultString = "二";
			break;
		case 3:
			resultString = "サン";
			break;
		case 4:
			resultString = "ヨン";
			break;
		case 5:
			resultString = "ゴ";
			break;
		case 6:
			if (position == 1) {
				resultString = "ロッ";
			} else {
				resultString = "ロク";
			}
			break;
		case 7:
			resultString = "ナナ";
			break;
		case 8:
			if (position == 0 || position == 1) {
				resultString = "ハッ";
			} else {
				resultString = "ハチ";
			}
			break;
		case 9:
			resultString = "キュウ";
			break;
		default:
			break;
		}
		return resultString ;
	}

	/**
	 * 4桁ごとの数から、各桁の数が入る配列を作ります。0も入ります。
	 * 
	 * @param fourDigitNumber
	 * @return 各桁の数が入る配列(0でも入る)
	 */
	private static short[] makeNumberArray(short fourDigitNumber) {
		short[] numberArray = new short[4];
		for (int j = numberArray.length - 1; j >= 0; j--) {
			numberArray[j] = (short) (fourDigitNumber % 10);
			fourDigitNumber = (short) (fourDigitNumber / 10);
		}
		return numberArray;
	}

	/**
	 * 4桁毎の位を文字列（String）で返します。
	 * 
	 * @param fourDigitIndex 4桁毎の位の何番目か。5はガイ。
     * @return 四桁毎の位（万、億、兆など）の文字列
	 */
	private static String getFourDigitPositionString(int fourDigitIndex) {
		String resultString = "" ;
		switch (fourDigitIndex) {
		case 5:
			resultString = "ガイ";
			break;
		case 4:
			resultString = "ケイ";
			break;
		case 3:
			resultString = "チョウ";
			break;
		case 2:
			resultString = "オク";
			break;
		case 1:
			resultString = "マン";
			break;
		case 0:
			break;
		default:
			break;
		}
		return resultString ;
	}

	/**
	 * 数から、四桁毎に数字が入る配列を作ります。
	 * 
	 * @param number 数
	 * @return 四桁毎に数字が入る配列
	 */
	private static short[] makeFourDigitNumberArray(Long number) {
		short[] tmpFourDigitNumberArray = new short[5];

		long tmpTargetNumber = number;
		int digitCount = 0;

		while (true) {
			tmpFourDigitNumberArray[digitCount] = (short) (tmpTargetNumber % 10000);
			tmpTargetNumber = tmpTargetNumber / 10000;
			if (tmpTargetNumber == 0) {
				break;
			}
			digitCount++;
		}
		short[] fourDigitNumberArray = new short[digitCount + 1];
		for (int i = 0; i < fourDigitNumberArray.length; i++) {
			fourDigitNumberArray[i] = tmpFourDigitNumberArray[i];
		}
		return fourDigitNumberArray;
	}
}

