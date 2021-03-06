// AUTOGENERATED CODE.  DO NOT MODIFY DIRECTLY!  Instead, please modify the transformer/AbstractPreparableTransformerX.ftl file.
// See the README in the module's src/template directory for details.
package com.linkedin.dagli.transformer;

import com.linkedin.dagli.transformer.internal.PreparableTransformer3InternalAPI;
import com.linkedin.dagli.preparer.Preparer3;
import com.linkedin.dagli.preparer.PreparerContext;
import com.linkedin.dagli.producer.Producer;


public abstract class AbstractPreparableTransformer3<A, B, C, R, N extends PreparedTransformer3<A, B, C, R>, S extends AbstractPreparableTransformer3<A, B, C, R, N, S>>
    extends AbstractTransformer3<A, B, C, R, PreparableTransformer3InternalAPI<A, B, C, R, N, S>, S> implements
    PreparableTransformer3<A, B, C, R, N> {

  private static final long serialVersionUID = 1;

  /**
   * Returns whether the preparer returned by {@link #getPreparer(PreparerContext)} is idempotent to identical
   * inputs; i.e. preparing the transformer with a sequence of distinct examples results in the same prepared
   * transformer as preparing with duplicate examples included.
   *
   * <strong>Idempotent does not imply commutative</strong>: an idempotent preparer may still be affected by the
   * <i>order</i> of the (de-duplicated) inputs, e.g. whether the first value A is seen before or after the first value
   * B is allowed to change the result.
   *
   * For example, the {@code Max} transformer calculates the maximum value of all its inputs, and duplicated inputs will
   * not affect the result--it is thus idempotent-preparable.  In contrast, a hypothetical {@code Count} transformer
   * that simply counts the number of examples would <strong>not</strong> be idempotent, as the total number of examples
   * determines the final prepared value (a non-idempotent-preparable transformer may still be constant-result: our
   * {@code Count} transformer would be constant-result since it would output the same total count for each example).
   *
   * The default implementation returns false.
   *
   * <strong>The determination of idempotency must be made independently of this transformer's parents in the DAG.
   * </strong>  More concretely, replacing the parents of this transformer with arbitrary (valid) substitutes should not
   * affect the returned value.  If this is impossible, this method should return false.
   *
   * In those rare cases where the prepared transformers "for new data" and "for preparation data" are different,
   * <strong>both</strong> must be idempotent to duplicated examples if this method returns true.
   *
   * The benefit of idempotency is that it allows for optimizations when reducing and executing the DAG that may result
   * in substantial improvements to execution speed.
   *
   * @return true if the transformer's preparer is idempotent to duplicated examples, false otherwise
   */
  protected boolean hasIdempotentPreparer() {
    return false;
  }

  @Override
  protected PreparableTransformer3InternalAPI<A, B, C, R, N, S> createInternalAPI() {
    return new InternalAPI();
  }

  protected class InternalAPI extends
      AbstractTransformer3<A, B, C, R, PreparableTransformer3InternalAPI<A, B, C, R, N, S>, S>.InternalAPI implements
      PreparableTransformer3InternalAPI<A, B, C, R, N, S> {
    @Override
    public Preparer3<A, B, C, R, N> getPreparer(PreparerContext context) {
      return AbstractPreparableTransformer3.this.getPreparer(context);
    }

    @Override
    public boolean hasIdempotentPreparer() {
      return AbstractPreparableTransformer3.this.hasIdempotentPreparer();
    }
  }

  protected abstract Preparer3<A, B, C, R, N> getPreparer(PreparerContext context);

  public AbstractPreparableTransformer3() {
    super();
  }

  public AbstractPreparableTransformer3(Producer<? extends A> input1, Producer<? extends B> input2,
      Producer<? extends C> input3) {
    super(input1, input2, input3);
  }
}
