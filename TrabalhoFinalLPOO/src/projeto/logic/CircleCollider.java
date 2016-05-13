package projeto.logic;

/**
 * @author David
 * @version 1.0
 * @created 03-mai-2016 15:30:30
 */
public class CircleCollider extends Collider {

	private double radius;

	public CircleCollider(double r){
		radius = r;
	} 
	
	public CircleCollider(double r, Vector2 pos, String t,boolean m,double ms){
		super(pos,t,m,ms);
		radius = r;
	}
	
	public CircleCollider(double r, double x, double y){
		radius = r;
		this.position.x = x;
		this.position.y = y; 
	}

	public double getMass(){
		return mass;
	}
	
	public void setMass(double m){
		this.mass = m;
	}
	
	@Override
	public Rectangulo getBoundingBox() {
		Rectangulo res = new Rectangulo(this.position.x,this.position.y,radius * 2);
		return res;
	}

	@Override
	public void onCollisionEnter(Collider c) {
	}


	@Override
	public void onTriggerEnter(Collider c) {
		// TODO Auto-generated method stub
		
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}