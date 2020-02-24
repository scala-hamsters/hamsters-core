package io.github.hamsters

import scala.language.implicitConversions

trait Monoid[T] {
  def empty: T
  def compose(l: T, r: T): T
}

class MonoidOps[T: Monoid](l: T) {
  def |+|(r: T): T = Monoid[T].compose(l, r)
}

object Monoid {

  implicit def type2Monoid[T: Monoid](t: T): MonoidOps[T] = new MonoidOps[T](t)

  def apply[T](implicit m: Monoid[T]): Monoid[T] = m

  implicit val booleanMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = false
    override def compose(l: Boolean, r: Boolean): Boolean = l || r
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    override def empty: Int = 0
    override def compose(l: Int, r: Int): Int = l + r
  }

  implicit val longMonoid: Monoid[Long] = new Monoid[Long] {
    override def empty: Long = 0l
    override def compose(l: Long, r: Long): Long = l + r
  }

  implicit val bigDecimalMonoid: Monoid[BigDecimal] = new Monoid[BigDecimal] {
    override def empty: BigDecimal = BigDecimal(0)
    override def compose(l: BigDecimal, r: BigDecimal): BigDecimal = l + r
  }

  // float and double monoid break the laws : https://github.com/scalaz/scalaz/issues/334
  implicit val floatMonoid: Monoid[Float] = new Monoid[Float] {
    override def empty: Float = 0f
    override def compose(l: Float, r: Float): Float = l + r
  }
  
  implicit val doubleMonoid: Monoid[Double] = new Monoid[Double] {
    override def empty: Double = 0d
    override def compose(l: Double, r: Double): Double = l + r
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    override def empty: String = ""
    override def compose(l: String, r: String): String = s"$l$r"
  }

  implicit def listMonoid[T]: Monoid[List[T]] = new Monoid[List[T]] {
    override def empty: List[T] = List.empty[T]
    override def compose(l: List[T], r: List[T]): List[T] = l ++ r
  }

  implicit def seqMonoid[T]: Monoid[Seq[T]] = new Monoid[Seq[T]] {
    override def empty: Seq[T] = Seq.empty[T]
    override def compose(l: Seq[T], r: Seq[T]): Seq[T] = l ++ r
  }

  implicit def optionMonoid[T]: Monoid[Option[T]] = new Monoid[Option[T]] {
    override def empty: Option[T] = None
    override def compose(l: Option[T], r: Option[T]): Option[T] = l orElse r
  }

}
