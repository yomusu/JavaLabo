
public class PassiveTimer {

	/** 設定期間 */
	protected final long	term;
	
	/** 開始時間 */
	protected long	startTime;
	
	/** 計測中フラグ */
	private boolean	counting;
	
	/** スタートした回数 */
	protected int		startCounter = 0;
	
	
	
	public PassiveTimer( long term ) {
		
		this.term = term;
	}
	
	
	/*****************************************
	 * 
	 * タイマーを開始する
	 * 
	 */
	public void start() {
		counting = true;
		startCounter++;
		startTime = System.currentTimeMillis();
	}
	
	
	/******************************************
	 * 
	 * タイマーが指定時間を過ぎたかどうかチェックする
	 * 
	 * @return
	 */
	public boolean check() {
		
		if( counting ) {
			long	delta = System.currentTimeMillis() - startTime;
			if( delta >= term ) {
				counting = false;
				return true;
			}
		}

		return false;
	}
	
	
	/*******************************************
	 * 
	 * startした回数を取得する
	 * 
	 * @return
	 */
	public int getStartCount() {
		return startCounter;
	}
	
	
	
	
	static public void main( String[] args ) {
		
		
		PassiveTimer	t = new PassiveTimer( 3000 );
		
		t.start();
		
		while( true ) {
			
			if( t.check() ) {
				System.out.println("Check!");
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch( InterruptedException e ) {}
		}
	}
}
